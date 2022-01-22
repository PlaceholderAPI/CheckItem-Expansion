package com.extendedclip.papi.expansion.checkitem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionType;

import me.clip.placeholderapi.PlaceholderAPI;
import me.clip.placeholderapi.PlaceholderAPIPlugin;
import me.clip.placeholderapi.expansion.Configurable;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;

public class CheckItemExpansion extends PlaceholderExpansion implements Configurable {
  
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
    return "2.4.2";
  }
  
  public class ItemWrapper {
    
    private boolean checkNameContains;
    private boolean checkNameStartsWith;
    private boolean checkNameEquals;
    private boolean checkLoreContains;
    private boolean checkLoreEquals;
    private boolean checkMaterialContains;
    private boolean checkDurability;
    private boolean checkCustomData;
    private boolean checkAmount;
    private boolean checkType;
    private boolean checkMainHand;
    private boolean checkOffHand;
    private boolean checkEnchantments;
    private boolean checkEnchanted;
    private boolean checkPotionType;
    private boolean checkPotionExtended;
    private boolean checkPotionUpgraded;
    private boolean isStrict;
    private boolean hdbItem;
    private boolean remove;
    private String material;
    private short data;
    private int customData;
    private int amount;
    private String name;
    private String lore;
    private String materialString;
    private HashMap<Enchantment, Integer> enchantments;
    private PotionType potionType;
    private boolean potionExtended;
    private boolean potionUpgraded;
    private int hdbId;
    private int slot;
    
    public ItemWrapper(String material, short data, int amt) {
      this.material = material.toUpperCase();
      this.data = data;
      this.amount = amt;
      slot = -1;
    }
    
    public ItemWrapper() {
      slot = -1;
    }
    
    @Override
    public String toString() {
      return "ItemWrapper [checkNameContains="
          + checkNameContains
          + ", checkNameStartsWith="
          + checkNameStartsWith
          + ", checkNameEquals="
          + checkNameEquals
          + ", checkLoreContains="
          + checkLoreContains
          + ", checkLoreEquals="
          + checkLoreEquals
          + ", checkMaterialContains="
          + checkMaterialContains
          + ", checkDurability="
          + checkDurability
          + ", checkCustomData="
          + checkCustomData
          + ", checkAmount="
          + checkAmount
          + ", checkType="
          + checkType
          + ", checkMainHand="
          + checkMainHand
          + ", checkOffHand="
          + checkOffHand
          + ", checkEnchantments="
          + checkEnchantments
          + ", checkEnchanted="
          + checkEnchanted
          + ", isStrict="
          + isStrict
          + ", hdbItem="
          + hdbItem
          + ", remove="
          + remove
          + ", material="
          + material
          + ", data="
          + data
          + ", customData="
          + customData
          + ", amount="
          + amount
          + ", name="
          + name
          + ", lore="
          + lore
          + ", materialString="
          + materialString
          + ", enchantments="
          + enchantments
          + ", potionType="
          + potionType
          + ", checkPotionExtended="
          + checkPotionExtended
          + ", potionExtended="
          + potionExtended
          + ", checkPotionUpgraded="
          + checkPotionUpgraded
          + ", potionUpgraded="
          + potionUpgraded
          + ", hdbId="
          + hdbId
          + ", slot="
          + slot
          + "]";
    }
    
    public String getType() {
      return this.material;
    }
    
    protected void setType(String material) {
      this.material = material.toUpperCase();
    }
    
    public short getDurability() {
      return this.data;
    }
    
    protected void setDurability(short durability) {
      this.data = durability;
    }
    
    public int getCustomData() {
      return this.customData;
    }
    
    protected void setCustomData(int customData) {
      this.customData = customData;
    }
    
    public int getAmount() {
      return this.amount;
    }
    
    protected void setAmount(int amount) {
      this.amount = amount;
    }
    
    public String getName() {
      return this.name;
    }
    
    protected void setName(String name) {
      this.name = name;
    }
    
    public String getLore() {
      return this.lore;
    }
    
    protected void setLore(String lore) {
      this.lore = lore;
    }
    
    public String getMaterialString() {
      return this.materialString;
    }
    
    protected void setMaterialString(String materialString) {
      this.materialString = materialString;
    }
    
    protected void setEnchantments(HashMap<Enchantment, Integer> enchantments) {
      this.enchantments = enchantments;
    }
    
    public HashMap<Enchantment, Integer> getEnchantments() {
      return this.enchantments;
    }
    
    protected void setPotionType(PotionType potionType) {
      this.potionType = potionType;
    }
    
    public PotionType getPotionType() {
      return this.potionType;
    }
    
    protected void setPotionExtended(boolean potionExtended) {
      this.potionExtended = potionExtended;
    }
    
    public boolean getPotionExtended() {
      return this.potionExtended;
    }
    
    protected void setPotionUpgraded(boolean potionUpgraded) {
      this.potionUpgraded = potionUpgraded;
    }
    
    public boolean getPotionUpgraded() {
      return this.potionUpgraded;
    }
    
    protected void setHdbId(int id) {
      this.hdbId = id;
    }
    
    public int getHdbId() {
      return this.hdbId;
    }
    
    protected void setCheckDurability(boolean checkDurability) {
      this.checkDurability = checkDurability;
    }
    
    public boolean shouldCheckDurability() {
      return this.checkDurability;
    }
    
    protected void setCheckCustomData(boolean checkCustomData) {
      this.checkCustomData = checkCustomData;
    }
    
    public boolean shouldCheckCustomData() {
      return this.checkCustomData;
    }
    
    protected void setCheckAmount(boolean checkAmount) {
      this.checkAmount = checkAmount;
    }
    
    public boolean shouldCheckAmount() {
      return this.checkAmount;
    }
    
    protected void setCheckNameContains(boolean checkNameContains) {
      this.checkNameContains = checkNameContains;
    }
    
    public boolean shouldCheckNameContains() {
      return this.checkNameContains;
    }
    
    protected void setCheckNameStartsWith(boolean checkNameStartsWith) {
      this.checkNameStartsWith = checkNameStartsWith;
    }
    
    public boolean shouldCheckNameStartsWith() {
      return this.checkNameStartsWith;
    }
    
    protected void setCheckNameEquals(boolean checkNameEquals) {
      this.checkNameEquals = checkNameEquals;
    }
    
    public boolean shouldCheckNameEquals() {
      return this.checkNameEquals;
    }
    
    protected void setCheckLoreContains(boolean checkLoreContains) {
      this.checkLoreContains = checkLoreContains;
    }
    
    public boolean shouldCheckLoreContains() {
      return this.checkLoreContains;
    }
    
    protected void setCheckLoreEquals(boolean checkLoreEquals) {
      this.checkLoreEquals = checkLoreEquals;
    }
    
    public boolean shouldCheckLoreEquals() {
      return this.checkLoreEquals;
    }
    
    protected void setCheckMaterialContains(boolean checkMaterialContains) {
      this.checkMaterialContains = checkMaterialContains;
    }
    
    public boolean shouldCheckMaterialContains() {
      return this.checkMaterialContains;
    }
    
    protected void setCheckType(boolean checkType) {
      this.checkType = checkType;
    }
    
    public boolean shouldCheckType() {
      return this.checkType;
    }
    
    protected void setCheckMainHand(boolean checkMainHand) {
      this.checkMainHand = checkMainHand;
    }
    
    public boolean shouldCheckMainHand() {
      return this.checkMainHand;
    }
    
    protected void setCheckOffHand(boolean checkOffHand) {
      this.checkOffHand = checkOffHand;
    }
    
    public boolean shouldCheckOffHand() {
      return this.checkOffHand;
    }
    
    protected void setIsStrict(boolean isStrict) {
      this.isStrict = isStrict;
    }
    
    public boolean isStrict() {
      return this.isStrict;
    }
    
    protected void setCheckEnchantments(boolean checkEnchantments) {
      this.checkEnchantments = checkEnchantments;
    }
    
    public boolean shouldCheckEnchantments() {
      return this.checkEnchantments;
    }
    
    protected void setCheckEnchanted(boolean checkEnchanted) {
      this.checkEnchanted = checkEnchanted;
    }
    
    public boolean shouldCheckEnchanted() {
      return this.checkEnchanted;
    }
    
    protected void setCheckPotionType(boolean checkPotionType) {
      this.checkPotionType = checkPotionType;
    }
    
    public boolean shouldCheckPotionType() {
      return this.checkPotionType;
    }
    
    protected void setCheckPotionExtended(boolean checkPotionExtended) {
      this.checkPotionExtended = checkPotionExtended;
    }
    
    public boolean shouldCheckPotionExtended() {
      return this.checkPotionExtended;
    }
    
    protected void setCheckPotionUpgraded(boolean checkPotionUpgraded) {
      this.checkPotionUpgraded = checkPotionUpgraded;
    }
    
    public boolean shouldCheckPotionUpgraded() {
      return this.checkPotionUpgraded;
    }
    
    protected void setRemove(boolean remove) {
      this.remove = remove;
    }
    
    public boolean shouldRemove() {
      return remove;
    }
    
    protected void setSlot(int slot) {
      this.slot = slot;
    }
    
    public int getSlot() {
      return slot;
    }
    
  }
  
  @SuppressWarnings("deprecation")
  public String onPlaceholderRequest(Player p, String args) {
    ItemWrapper wrapper = new ItemWrapper();
    ItemStack[] itemsToCheck;
    boolean amount = false;
    args = PlaceholderAPI.setBracketPlaceholders(p, args);
    if (args.startsWith("give_")) {
      if (!((boolean) get("give_enabled", true))) {
        return "Give placeholders have been disabled. Check PlaceholderAPI Config.";
      }
      args = args.replace("give_", "");
      wrapper = getWrapper(wrapper, ChatColor.translateAlternateColorCodes('&', args), p);
      if (wrapper == null) {
        return null;
      }
      return giveItem(wrapper, p);
    }
    if (args.split("_")[0].startsWith("getinfo:")) {
      args = args.replace("getinfo:", "");
      String[] argsSplit = args.split("_", 2);
      int slot = 0;
      switch (argsSplit[0]) {
        case "mainhand":
          slot = p.getInventory().getHeldItemSlot();
          break;
        case "offhand":
          slot = 40;
          break;
        default:
          try {
            slot = Integer.parseInt(argsSplit[0]);
          } catch (NumberFormatException e) {
            return "Invalid number for slot";
          }
          break;
      }
      wrapper = getWrapper(wrapper, ChatColor.translateAlternateColorCodes('&', argsSplit[1]), p);
      ItemStack item = p.getInventory().getItem(slot);
      if (item == null) {
        return "";
      }
      String data = "";
      if ((wrapper.checkNameContains || wrapper.checkNameEquals || wrapper.checkNameStartsWith)
          && (item.hasItemMeta() && item.getItemMeta().hasDisplayName()))
        data += item.getItemMeta().getDisplayName() + " &r";
      if (wrapper.checkType)
        data += item.getType() + " &r";
      if (wrapper.checkAmount)
        data += item.getAmount() + " &r";
      if (wrapper.checkDurability)
        data += item.getDurability() + " &r";
      if (wrapper.checkCustomData && item.hasItemMeta() && item.getItemMeta().hasCustomModelData())
        data += item.getItemMeta().getCustomModelData() + " &r";
      if ((wrapper.checkLoreContains || wrapper.checkLoreEquals)
          && (item.hasItemMeta() && item.getItemMeta().hasLore())) {
        for (String s : item.getItemMeta().getLore()) {
          data += s + "|";
        }
        data = data.substring(0, data.length() - 1) + " &r";
      }
      if (wrapper.checkEnchantments && item.hasItemMeta() && item.getItemMeta().hasEnchants()) {
        for (Entry<Enchantment, Integer> entry : item.getItemMeta().getEnchants().entrySet()) {
          data += entry.getKey().getKey() + ":" + entry.getValue() + "|";
        }
        data = data.substring(0, data.length() - 1) + " &r";
      }
      if (wrapper.checkEnchanted && item.hasItemMeta())
        data += item.getItemMeta().hasEnchants() + " &r";
      if (wrapper.checkPotionType && item.hasItemMeta() && item.getItemMeta() instanceof PotionMeta) {
        PotionData potionData = ((PotionMeta) item.getItemMeta()).getBasePotionData();
        data += potionData.getType() + " &r";
      }
      if (wrapper.checkPotionExtended && item.hasItemMeta() && item.getItemMeta() instanceof PotionMeta) {
        PotionData potionData = ((PotionMeta) item.getItemMeta()).getBasePotionData();
        data += potionData.isExtended() + " &r";
      }
      if (wrapper.checkPotionUpgraded && item.hasItemMeta() && item.getItemMeta() instanceof PotionMeta) {
        PotionData potionData = ((PotionMeta) item.getItemMeta()).getBasePotionData();
        data += potionData.isUpgraded() + " &r";
      }
      return data.substring(0, data.length() - 3);
    }
    if (args.startsWith("amount_")) {
      args = args.replace("amount_", "");
      amount = true;
    }
    if (args.startsWith("remove_")) {
      if (!((boolean) get("remove_enabled", true))) {
        return "Remove placeholders have been disabled. Check PlaceholderAPI Config.";
      }
      wrapper.setRemove(true);
      args = args.replace("remove_", "");
    }
    wrapper = getWrapper(wrapper, ChatColor.translateAlternateColorCodes('&', args), p);
    
    if (wrapper == null) {
      return null;
    }
    if (wrapper.shouldCheckMainHand() || wrapper.shouldCheckOffHand()) {
      try {
        Class.forName("org.bukkit.inventory.PlayerInventory").getMethod("getItemInMainHand", null);
        if (wrapper.shouldCheckMainHand() && wrapper.shouldCheckOffHand()) {
          itemsToCheck = new ItemStack[2];
          itemsToCheck[0] = (p.getInventory().getItem(p.getInventory().getHeldItemSlot()));
          itemsToCheck[1] = (p.getInventory().getItem(40));
        } else if (wrapper.shouldCheckMainHand()) {
          itemsToCheck = new ItemStack[1];
          itemsToCheck[0] = (p.getInventory().getItem(p.getInventory().getHeldItemSlot()));
        } else {
          itemsToCheck = new ItemStack[1];
          itemsToCheck[0] = (p.getInventory().getItem(40));
        }
        
      } catch (NoSuchMethodException e) {
        itemsToCheck = new ItemStack[1];
        itemsToCheck[0] = p.getInventory().getItem(p.getInventory().getHeldItemSlot());
      } catch (Exception e) {
        e.printStackTrace();
        return "error";
      }
    } else if (wrapper.getSlot() != -1) {
      itemsToCheck = new ItemStack[1];
      itemsToCheck[0] = p.getInventory().getItem(wrapper.getSlot());
    } else {
      if (wrapper.shouldCheckType() && wrapper.getType().equals("AIR")) {
        return p.getInventory().firstEmpty() == -1 ? PlaceholderAPIPlugin.booleanFalse()
            : PlaceholderAPIPlugin.booleanTrue();
      }
      itemsToCheck = p.getInventory().getContents();
    }
    
    if (amount) {
      return String.valueOf(getItemAmount(wrapper, p, itemsToCheck));
    } else {
      return checkItem(wrapper, p, itemsToCheck) ? PlaceholderAPIPlugin.booleanTrue()
          : PlaceholderAPIPlugin.booleanFalse();
    }
  }
  
  @SuppressWarnings("deprecation")
  private String giveItem(ItemWrapper wrapper, Player p) {
    ItemStack item = new ItemStack(Material.getMaterial(wrapper.getType()));
    ItemMeta meta = item.getItemMeta();
    if (wrapper.shouldCheckDurability()) {
      try {
        Class.forName("org.bukkit.inventory.meta.Damageable");
        if (meta instanceof Damageable) {
          Damageable dmg = (Damageable) meta;
          dmg.setDamage(wrapper.getDurability());
        }
      } catch (ClassNotFoundException e) {
        item.setDurability(wrapper.getDurability());
      } catch (Exception e) {
        e.printStackTrace();
        return "error";
      }
    }
    if (wrapper.shouldCheckCustomData()) {
      meta.setCustomModelData(wrapper.getCustomData());
    }
    if (wrapper.shouldCheckNameEquals())
      meta.setDisplayName(wrapper.getName());
    if (wrapper.shouldCheckLoreEquals()) {
      List<String> lore = Stream.of(wrapper.getLore().split("\\|", -1)).collect(Collectors.toList());
      meta.setLore(lore);
    }
    if (wrapper.shouldCheckEnchantments()) {
      for (Entry<Enchantment, Integer> e : wrapper.getEnchantments().entrySet()) {
        meta.addEnchant(e.getKey(), (e.getValue() == -1 ? 1 : e.getValue()), true);
      }
    }
    if (wrapper.shouldCheckPotionType()) {
      if (meta instanceof PotionMeta) {
        PotionMeta potionMeta = (PotionMeta) meta;
        potionMeta.setBasePotionData(
            new PotionData(wrapper.getPotionType(), wrapper.getPotionExtended(), wrapper.getPotionUpgraded()));
      }
    }
    item.setItemMeta(meta);
    
    if (wrapper.shouldCheckAmount()) {
      int remaining = wrapper.getAmount();
      while (remaining > 0) {
        HashMap<Integer, ItemStack> returned;
        if (remaining > 64) {
          item.setAmount(64);
          returned = p.getInventory().addItem(item);
          remaining -= 64;
        } else {
          item.setAmount(remaining);
          returned = p.getInventory().addItem(item);
          remaining = 0;
        }
        if (!returned.isEmpty()) {
          for (Entry<Integer, ItemStack> e : returned.entrySet()) {
            return (e.getValue().getAmount() + remaining) + "";
          }
        }
      }
    } else {
      p.getInventory().addItem(item);
    }
    return "yes";
  }
  
  private boolean checkItem(ItemWrapper wrapper, Player p, ItemStack... items) {
    int total = getItemAmount(wrapper, p, items);
    if (wrapper.shouldCheckAmount()) {
      if (wrapper.isStrict()) {
        return total == wrapper.getAmount();
      }
      return total >= wrapper.getAmount();
    }
    
    return total >= 1;
  }
  
  @SuppressWarnings("deprecation")
  private int getItemAmount(ItemWrapper wrapper, Player p, ItemStack... items) {
    int total = 0;
    List<ItemStack> matched = new ArrayList<ItemStack>();
    itemsLoop: for (ItemStack toCheck : items) {
      if (toCheck == null && wrapper.shouldCheckType() && wrapper.getType().equals("AIR")) {
        return Integer.MAX_VALUE;
      }
      if (toCheck != null && toCheck.getType() != Material.AIR) {
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
        if (wrapper.shouldCheckLoreEquals()) {
          if (!toCheckMeta.hasLore())
            continue;
          String lore = String.join("|", toCheckMeta.getLore());
          if (!wrapper.getLore().equals(lore))
            continue;
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
        if (wrapper.shouldCheckMaterialContains()) {
          if (!toCheck.getType().name().contains(wrapper.getMaterialString())) {
            continue;
          }
        }
        
        if (wrapper.shouldCheckEnchantments()) {
          if (toCheckMeta.getEnchants().isEmpty())
            continue;
          Map<Enchantment, Integer> toCheckEnchants = toCheckMeta.getEnchants();
          for (Entry<Enchantment, Integer> e : wrapper.getEnchantments().entrySet()) {
            if (!toCheckEnchants.containsKey(e.getKey())) {
              continue itemsLoop;
            }
            if (e.getValue() != -1 && !(toCheckEnchants.get(e.getKey()) == e.getValue())) {
              continue itemsLoop;
            }
          }
        }
        
        if (wrapper.shouldCheckPotionType()
            || wrapper.shouldCheckPotionExtended()
            || wrapper.shouldCheckPotionUpgraded()) {
          if (!(toCheckMeta instanceof PotionMeta))
            continue;
          PotionData potionData = ((PotionMeta) toCheckMeta).getBasePotionData();
          if (wrapper.shouldCheckPotionType()
              && (potionData.getType() == null || potionData.getType() != wrapper.getPotionType()))
            continue;
          if (wrapper.shouldCheckPotionExtended() && potionData.isExtended() != wrapper.getPotionExtended())
            continue;
          if (wrapper.shouldCheckPotionUpgraded() && potionData.isUpgraded() != wrapper.getPotionUpgraded())
            continue;
        }
        
        if (wrapper.shouldCheckEnchanted() && toCheckMeta.getEnchants().isEmpty()) {
          if (toCheckMeta instanceof EnchantmentStorageMeta) {
            if (((EnchantmentStorageMeta) toCheckMeta).getStoredEnchants().isEmpty()) {
              continue;
            }
          } else {
            continue;
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
        matched.add(toCheck);
      }
    }
    if (wrapper.shouldRemove()) {
      boolean remove = true;
      if (wrapper.shouldCheckAmount()) {
        remove = total >= wrapper.getAmount();
        if (remove)
          total = wrapper.getAmount();
      }
      if (remove) {
        ItemStack[] matchedArr = new ItemStack[matched.size()];
        if (wrapper.shouldCheckAmount()) {
          int remaining = wrapper.getAmount();
          ItemStack[] armor = p.getInventory().getArmorContents();
          for (int i = 0; i < armor.length; i++) {
            if (matched.contains(armor[i])) {
              if (armor[i].getAmount() > remaining) {
                armor[i].setAmount(armor[i].getAmount() - remaining);
                remaining = 0;
                break;
              } else {
                remaining -= armor[i].getAmount();
                armor[i] = null;
              }
            }
          }
          p.getInventory().setArmorContents(armor);
          ItemStack offhand = p.getInventory().getItemInOffHand();
          if (matched.contains(offhand)) {
            if (offhand.getAmount() > remaining) {
              offhand.setAmount(offhand.getAmount() - remaining);
              remaining = 0;
            } else {
              remaining -= offhand.getAmount();
              offhand = null;
            }
          }
          p.getInventory().setItemInOffHand(offhand);
          for (int i = 0; i < matched.size() && remaining > 0; i++) {
            ItemStack item = matched.get(i);
            int match = p.getInventory().first(item);
            if (match == -1) {
              break;
            }
            ItemStack matchedItem = p.getInventory().getItem(match);
            if (matchedItem.getAmount() > remaining) {
              matchedItem.setAmount(matchedItem.getAmount() - remaining);
              remaining = 0;
              break;
            } else {
              remaining -= matchedItem.getAmount();
              p.getInventory().clear(match);
            }
          }
        } else {
          p.getInventory().removeItem(matched.toArray(matchedArr));
          ItemStack[] armor = p.getInventory().getArmorContents();
          for (int i = 0; i < armor.length; i++) {
            if (matched.contains(armor[i])) {
              armor[i] = null;
            }
          }
          p.getInventory().setArmorContents(armor);
          ItemStack offhand = p.getInventory().getItemInOffHand();
          if (matched.contains(offhand)) {
            offhand = null;
          }
          p.getInventory().setItemInOffHand(offhand);
        }
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
  
  @SuppressWarnings("deprecation")
  private ItemWrapper getWrapper(ItemWrapper wrapper, String input, Player p) {
    String[] arrayOfString;
    int j = (arrayOfString = input.split("(?<!\\\\),")).length;
    for (int i = 0; i < j; i++) {
      String part = arrayOfString[i].replaceAll("\\\\,", ",");
      if (part.startsWith("data:")) {
        part = part.replace("data:", "");
        try {
          wrapper.setDurability(Short.parseShort(PlaceholderAPI.setBracketPlaceholders(p, part)));
          wrapper.setCheckDurability(true);
          continue;
        } catch (Exception localException1) {
          continue;
        }
      }
      if (part.startsWith("custommodeldata:")) {
        part = part.replace("custommodeldata:", "");
        wrapper.setCustomData(getInt(PlaceholderAPI.setBracketPlaceholders(p, part)));
        wrapper.setCheckCustomData(true);
        continue;
      }
      if (part.startsWith("mat:")) {
        part = part.replace("mat:", "");
        wrapper.setType(PlaceholderAPI.setBracketPlaceholders(p, part));
        wrapper.setCheckType(true);
        continue;
      }
      if (part.startsWith("amt:")) {
        part = part.replace("amt:", "");
        wrapper.setAmount(getInt(PlaceholderAPI.setBracketPlaceholders(p, part)));
        wrapper.setCheckAmount(true);
        continue;
      }
      if (part.startsWith("namestartswith:")) {
        part = part.replace("namestartswith:", "");
        wrapper.setName(PlaceholderAPI.setBracketPlaceholders(p, part));
        wrapper.setCheckNameStartsWith(true);
        continue;
      }
      if (part.startsWith("namecontains:")) {
        part = part.replace("namecontains:", "");
        wrapper.setName(PlaceholderAPI.setBracketPlaceholders(p, part));
        wrapper.setCheckNameContains(true);
        continue;
      }
      if (part.startsWith("nameequals:")) {
        part = part.replace("nameequals:", "");
        wrapper.setName(PlaceholderAPI.setBracketPlaceholders(p, part));
        wrapper.setCheckNameEquals(true);
        continue;
      }
      if (part.startsWith("lorecontains:")) {
        part = part.replace("lorecontains:", "");
        wrapper.setLore(PlaceholderAPI.setBracketPlaceholders(p, part));
        wrapper.setCheckLoreContains(true);
        continue;
      }
      if (part.startsWith("loreequals:")) {
        part = part.replace("loreequals:", "");
        wrapper.setLore(PlaceholderAPI.setBracketPlaceholders(p, part));
        wrapper.setCheckLoreEquals(true);
        continue;
      }
      if (part.startsWith("matcontains:")) {
        part = part.replace("matcontains:", "");
        wrapper.setMaterialString(PlaceholderAPI.setBracketPlaceholders(p, part));
        wrapper.setCheckMaterialContains(true);
        continue;
      }
      if (part.startsWith("enchantments:")) {
        part = part.replace("enchantments:", "");
        HashMap<Enchantment, Integer> enchantments = new HashMap<>();
        String[] enchArray = part.split(";");
        try { //This try is possibly useless, mending seems to work with getByName as well, no idea how I fixed it. Issue #10
          Class.forName("org.bukkit.enchantments.Enchantment").getMethod("getByKey", NamespacedKey.class);
          for (String s : enchArray) {
            String[] ench;
            if ((ench = s.split("=")).length > 1) {
              NamespacedKey key = NamespacedKey
                  .minecraft(PlaceholderAPI.setBracketPlaceholders(p, ench[0]).toLowerCase());
              enchantments.put(Enchantment.getByKey(key),
                  Integer.valueOf(PlaceholderAPI.setBracketPlaceholders(p, ench[1])));
            } else {
              NamespacedKey key = NamespacedKey.minecraft(PlaceholderAPI.setBracketPlaceholders(p, s).toLowerCase());
              enchantments.put(Enchantment.getByKey(key), -1);
            }
          }
        } catch (NoSuchMethodException | NoClassDefFoundError e) {
          for (String s : enchArray) {
            String[] ench;
            if ((ench = s.split("=")).length > 1) {
              enchantments.put(Enchantment.getByName(PlaceholderAPI.setBracketPlaceholders(p, ench[0]).toUpperCase()),
                  Integer.valueOf(PlaceholderAPI.setBracketPlaceholders(p, ench[1])));
            } else {
              enchantments.put(Enchantment.getByName(PlaceholderAPI.setBracketPlaceholders(p, s).toUpperCase()), -1);
            }
          }
        } catch (Exception e) {
          e.printStackTrace();
        }
        wrapper.setEnchantments(enchantments);
        wrapper.setCheckEnchantments(true);
        continue;
      }
      if (part.startsWith("potiontype:")) {
        part = part.replace("potiontype:", "");
        try {
          wrapper.setPotionType(PotionType.valueOf(part.toUpperCase()));
        } catch (IllegalArgumentException e) {
        }
        wrapper.setCheckPotionType(true);
        continue;
      }
      if (part.startsWith("potionextended:")) {
        part = part.replace("potionextended:", "");
        wrapper.setPotionExtended(Boolean.parseBoolean(part));
        wrapper.setCheckPotionExtended(true);
        continue;
      }
      if (part.startsWith("potionupgraded:")) {
        part = part.replace("potionupgraded:", "");
        wrapper.setCheckPotionUpgraded(true);
        wrapper.setPotionUpgraded(Boolean.parseBoolean(part));
        continue;
      }
      if (part.startsWith("inslot:")) {
        part = part.replace("inslot:", "");
        wrapper.setSlot(getInt(PlaceholderAPI.setBracketPlaceholders(p, part)));
        continue;
      }
      if (part.startsWith("inhand")) {
        if (part.startsWith("inhand:")) {
          part = part.replace("inhand:", "");
          if (part.equals("main"))
            wrapper.setCheckMainHand(true);
          else if (part.equals("off"))
            wrapper.setCheckOffHand(true);
        } else {
          wrapper.setCheckMainHand(true);
          wrapper.setCheckOffHand(true);
        }
        
        continue;
      }
      if (part.equals("strict")) {
        wrapper.setIsStrict(true);
        continue;
      }
      if (part.equals("enchanted")) {
        wrapper.setCheckEnchanted(true);
        continue;
      }
      
    }
    return wrapper;
  }
  
  @Override
  public Map<String, Object> getDefaults() {
    Map<String, Object> defaults = new HashMap<>();
    defaults.put("give_enabled", true);
    defaults.put("remove_enabled", true);
    return defaults;
  }
}
