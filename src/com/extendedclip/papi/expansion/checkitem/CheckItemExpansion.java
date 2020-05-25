package com.extendedclip.papi.expansion.checkitem;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.clip.placeholderapi.PlaceholderAPIPlugin;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;

public class CheckItemExpansion extends PlaceholderExpansion {
	
	public boolean canRegister() {
		return true;
	}
	
	public String getAuthor() {
		return "cj89898";
	}
	
	public String getIdentifier() {
		return "checkitem";
	}
	
	public String getVersion() {
		return "1.8.0";
	}
	
	public class ItemWrapper {
		
		private boolean checkNameContains;
		private boolean checkNameStartsWith;
		private boolean checkNameEquals;
		private boolean checkLoreContains;
		private boolean checkDurability;
		private boolean checkCustomData;
		private boolean checkAmount;
		private boolean checkType;
		private boolean checkHand;
		private boolean checkEnchantments;
		private boolean isStrict;
		private boolean hdbItem;
		private String material;
		private short data;
		private int customData;
		private int amount;
		private String name;
		private String lore;
		private HashMap<Enchantment, Integer> enchantments;
		private int hdbId;
		
		public ItemWrapper(String material, short data, int amt) {
			this.material = material.toUpperCase();
			this.data = data;
			this.amount = amt;
		}
		
		public ItemWrapper() {
		}
		
		public String getType() {
			return this.material;
		}
		
		public void setType(String material) {
			this.material = material.toUpperCase();
		}
		
		public short getDurability() {
			return this.data;
		}
		
		public void setDurability(short durability) {
			this.data = durability;
		}
		
		public int getCustomData() {
			return this.customData;
		}
		
		public void setCustomData(int customData) {
			this.customData = customData;
		}
		
		public int getAmount() {
			return this.amount;
		}
		
		public void setAmount(int amount) {
			this.amount = amount;
		}
		
		public String getName() {
			return this.name;
		}
		
		public void setName(String name) {
			this.name = name;
		}
		
		public void setLore(String lore) {
			this.lore = lore;
		}
		
		public String getLore() {
			return this.lore;
		}
		
		public void setEnchantments(HashMap<Enchantment, Integer> enchantments) {
			this.enchantments = enchantments;
		}
		
		public HashMap<Enchantment, Integer> getEnchantments() {
			return this.enchantments;
		}
		
		public void setHdbId(int id) {
			this.hdbId = id;
		}
		
		public int getHdbId() {
			return this.hdbId;
		}
		
		public boolean shouldCheckDurability() {
			return this.checkDurability;
		}
		
		public void setCheckDurability(boolean checkDurability) {
			this.checkDurability = checkDurability;
		}
		
		public boolean shouldCheckCustomData() {
			return this.checkCustomData;
		}
		
		public void setCheckCustomData(boolean checkCustomData) {
			this.checkCustomData = checkCustomData;
		}
		
		public boolean shouldCheckAmount() {
			return this.checkAmount;
		}
		
		public void setCheckAmount(boolean checkAmount) {
			this.checkAmount = checkAmount;
		}
		
		public boolean shouldCheckNameContains() {
			return this.checkNameContains;
		}
		
		public void setCheckNameContains(boolean checkNameContains) {
			this.checkNameContains = checkNameContains;
		}
		
		public boolean shouldCheckNameStartsWith() {
			return this.checkNameStartsWith;
		}
		
		public void setCheckNameStartsWith(boolean checkNameStartsWith) {
			this.checkNameStartsWith = checkNameStartsWith;
		}
		
		public boolean shouldCheckNameEquals() {
			return this.checkNameEquals;
		}
		
		public void setCheckNameEquals(boolean checkNameEquals) {
			this.checkNameEquals = checkNameEquals;
		}
		
		public boolean shouldCheckLoreContains() {
			return this.checkLoreContains;
		}
		
		public void setCheckLoreContains(boolean checkLoreContains) {
			this.checkLoreContains = checkLoreContains;
		}
		
		public boolean shouldCheckType() {
			return this.checkType;
		}
		
		public void setCheckType(boolean checkType) {
			this.checkType = checkType;
		}
		
		public boolean shouldCheckHand() {
			return this.checkHand;
		}
		
		public void setCheckHand(boolean checkHand) {
			this.checkHand = checkHand;
		}
		
		public boolean isStrict() {
			return this.isStrict;
		}
		
		public void setIsStrict(boolean isStrict) {
			this.isStrict = isStrict;
		}
		
		public boolean shouldCheckEnchantments() {
			return this.checkEnchantments;
		}
		
		public void setCheckEnchantments(boolean checkEnchantments) {
			this.checkEnchantments = checkEnchantments;
		}
	}
	
	public String onPlaceholderRequest(Player p, String args) {
		ItemWrapper wrapper;
		if (args.startsWith("amount_")) {
			wrapper = getItem(ChatColor.translateAlternateColorCodes('&', args.replace("amount_", "")));
			if (wrapper == null)
				return null;
			
			return String.valueOf(getItemAmount(wrapper, p.getInventory().getContents()));
		}
		wrapper = getItem(ChatColor.translateAlternateColorCodes('&', args));
		if (wrapper == null) {
			return null;
		}
		if (wrapper.shouldCheckType() && wrapper.getType().equals("AIR")) {
			return p.getInventory().firstEmpty() == -1 ? PlaceholderAPIPlugin.booleanFalse()
					: PlaceholderAPIPlugin.booleanTrue();
		}
		if (wrapper.shouldCheckHand()) {
			try {
				Class.forName("org.bukkit.inventory.PlayerInventory").getMethod("getItemInMainHand", null);
				ItemStack[] hands = new ItemStack[2];
				hands[0] = p.getInventory().getItemInMainHand();
				hands[1] = p.getInventory().getItemInOffHand();
				if (checkItem(wrapper, hands)) {
					return PlaceholderAPIPlugin.booleanTrue();
				}
				return PlaceholderAPIPlugin.booleanFalse();
			} catch (NoSuchMethodException e) {
				if (checkItem(wrapper, p.getInventory().getItemInHand())) {
					return PlaceholderAPIPlugin.booleanTrue();
				}
				return PlaceholderAPIPlugin.booleanFalse();
			} catch (Exception e) {
				return PlaceholderAPIPlugin.booleanFalse();
			}
		}
		ItemStack[] arrayOfItemStack = p.getInventory().getContents();
		return checkItem(wrapper, arrayOfItemStack) ? PlaceholderAPIPlugin.booleanTrue()
				: PlaceholderAPIPlugin.booleanFalse();
	}
	
	private boolean checkItem(ItemWrapper wrapper, ItemStack... items) {
		int total = getItemAmount(wrapper, items);
		if (wrapper.shouldCheckAmount()) {
			if (wrapper.isStrict()) {
				return total == wrapper.getAmount();
			}
			return total >= wrapper.getAmount();
		}
		
		return total >= 1;
	}
	
	private int getItemAmount(ItemWrapper wrapper, ItemStack... items) {
		int total = 0;
		itemsLoop: for (ItemStack toCheck : items) {
			if (toCheck != null) {
				if (wrapper.shouldCheckType() && !(wrapper.getType().equals(toCheck.getType().name()))) {
					continue;
				}
				if (wrapper.shouldCheckDurability() && !(wrapper.getDurability() == toCheck.getDurability())) {
					continue;
				}
				ItemMeta toCheckMeta = toCheck.getItemMeta();
				if (wrapper.shouldCheckCustomData()) {
					try {
						if (!toCheckMeta.hasCustomModelData())
							continue;
						if (wrapper.getCustomData() != toCheckMeta.getCustomModelData()) {
							continue;
						}
					} catch (NoSuchMethodError e) {
						PlaceholderAPIPlugin.getInstance().getLogger().log(Level.WARNING,
								"[CheckItem Expansion] CustomModelData doesn't exist before 1.14!");
					}
				}
				if (wrapper.shouldCheckLoreContains()) {
					if (!toCheckMeta.hasLore())
						continue;
					boolean loreContains = false;
					for (String line : toCheckMeta.getLore()) {
						if (line.contains(wrapper.getLore())) {
							loreContains = true;
							break;
						}
					}
					if (!loreContains) {
						continue;
					}
				}
				if (wrapper.shouldCheckNameContains()) {
					if (!(toCheckMeta.hasDisplayName() && toCheckMeta.getDisplayName().contains(wrapper.getName()))) {
						continue;
					}
				} else if (wrapper.shouldCheckNameStartsWith()) {
					if (!(toCheckMeta.hasDisplayName() && toCheckMeta.getDisplayName().startsWith(wrapper.getName()))) {
						continue;
					}
				} else if (wrapper.shouldCheckNameEquals()) {
					if (!(toCheckMeta.hasDisplayName() && toCheckMeta.getDisplayName().equals(wrapper.getName()))) {
						continue;
					}
				}
				
				if (wrapper.shouldCheckEnchantments()) {
					if (toCheck.getEnchantments() == null)
						continue;
					for (Entry<Enchantment, Integer> e : wrapper.getEnchantments().entrySet()) {
						if (!toCheck.containsEnchantment(e.getKey())) {
							continue itemsLoop;
						}
						if (e.getValue() != -1 && !(toCheck.getEnchantmentLevel(e.getKey()) == e.getValue())) {
							continue itemsLoop;
						}
					}
				}
				
				if (wrapper.isStrict() && wrapper.shouldCheckType()) {
					if (!wrapper.shouldCheckNameContains()
							&& !wrapper.shouldCheckNameEquals()
							&& !wrapper.shouldCheckNameStartsWith()
							&& toCheckMeta.hasDisplayName()) {
						continue;
					}
					if (!wrapper.shouldCheckLoreContains() && toCheckMeta.hasLore()) {
						continue;
					}
					if (!wrapper.shouldCheckDurability() && toCheck.getDurability() != 0) {
						continue;
					}
					if (!wrapper.shouldCheckEnchantments() && !toCheck.getEnchantments().isEmpty()) {
						continue;
					}
				}
				total += toCheck.getAmount();
			}
		}
		return total;
	}
	
	private int getInt(String s) {
		try {
			return Integer.parseInt(s);
		} catch (NumberFormatException ex) {
			return -1;
		}
	}
	
	private ItemWrapper getItem(String input) {
		ItemWrapper wrapper = new ItemWrapper();
		String[] arrayOfString;
		int j = (arrayOfString = input.split(",")).length;
		for (int i = 0; i < j; i++) {
			String part = arrayOfString[i];
			if (part.startsWith("data:")) {
				part = part.replace("data:", "");
				try {
					wrapper.setDurability(Short.parseShort(part));
					wrapper.setCheckDurability(true);
					continue;
				} catch (Exception localException1) {
					continue;
				}
			}
			if (part.startsWith("custommodeldata:")) {
				part = part.replace("custommodeldata:", "");
				wrapper.setCustomData(getInt(part));
				wrapper.setCheckCustomData(true);
				continue;
			}
			if (part.startsWith("mat:")) {
				part = part.replace("mat:", "");
				wrapper.setType(part);
				wrapper.setCheckType(true);
				continue;
			}
			if (part.startsWith("amt:")) {
				part = part.replace("amt:", "");
				wrapper.setAmount(getInt(part));
				wrapper.setCheckAmount(true);
				continue;
			}
			if (part.startsWith("namestartswith:")) {
				part = part.replace("namestartswith:", "");
				wrapper.setName(part);
				wrapper.setCheckNameStartsWith(true);
				continue;
			}
			if (part.startsWith("namecontains:")) {
				part = part.replace("namecontains:", "");
				wrapper.setName(part);
				wrapper.setCheckNameContains(true);
				continue;
			}
			if (part.startsWith("nameequals:")) {
				part = part.replace("nameequals:", "");
				wrapper.setName(part);
				wrapper.setCheckNameEquals(true);
				continue;
			}
			if (part.startsWith("lorecontains:")) {
				part = part.replace("lorecontains:", "");
				wrapper.setLore(part);
				wrapper.setCheckLoreContains(true);
				continue;
			}
			if (part.startsWith("enchantments:")) {
				part = part.replace("enchantments:", "");
				HashMap<Enchantment, Integer> enchantments = new HashMap<>();
				String[] enchArray = part.split(";");
				for (String s : enchArray) {
					String[] ench;
					try { //This try is possibly useless, mending seems to work with getByName as well, no idea how I fixed it. Issue #10
						Class.forName("org.bukkit.enchantments.Enchantment").getMethod("getByKey", NamespacedKey.class);
						if ((ench = s.split("=")).length > 1) {
							NamespacedKey key = NamespacedKey.minecraft(ench[0].toUpperCase());
							Bukkit.getLogger()
									.info(ench[0].toUpperCase() + ", " + key.getNamespace() + ", " + key.getKey());
							enchantments.put(Enchantment.getByKey(key), Integer.valueOf(ench[1]));
						} else {
							NamespacedKey key = NamespacedKey.minecraft(s.toUpperCase());
							enchantments.put(Enchantment.getByKey(key), -1);
						}
						
					} catch (NoSuchMethodError e) {
						if ((ench = s.split("=")).length > 1) {
							enchantments.put(Enchantment.getByName(ench[0].toUpperCase()), Integer.valueOf(ench[1]));
						} else {
							enchantments.put(Enchantment.getByName(s.toUpperCase()), -1);
						}
					} catch (Exception e) {
					}
					
				}
				wrapper.setEnchantments(enchantments);
				wrapper.setCheckEnchantments(true);
				continue;
			}
			if (part.equals("inhand")) {
				wrapper.setCheckHand(true);
				continue;
			}
			if (part.equals("strict")) {
				wrapper.setIsStrict(true);
			}
			
		}
		return wrapper;
	}
}
