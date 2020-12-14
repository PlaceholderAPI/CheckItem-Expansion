package com.extendedclip.papi.expansion.checkitem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionType;

import me.clip.placeholderapi.PlaceholderAPI;
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
    return "1.9.5";
  }
  
  public class ItemWrapper {
    
    private boolean checkNameContains;
    private boolean checkNameStartsWith;
    private boolean checkNameEquals;
    private boolean checkLoreContains;
    private boolean checkMaterialContains;
    private boolean checkDurability;
    private boolean checkCustomData;
    private boolean checkAmount;
    private boolean checkType;
    private boolean checkHand;
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
    
    public ItemWrapper(String material, short data, int amt) {
      this.material = material.toUpperCase();
      this.data = data;
      this.amount = amt;
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
          + ", checkHand="
          + checkHand
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
          + ", potionExtended="
          + checkPotionExtended
          + ", potionUpgraded="
          + checkPotionUpgraded
          + ", hdbId="
          + hdbId
          + "]";
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
    
    public String getMaterialString() {
      return this.materialString;
    }
    
    public void setMaterialString(String materialString) {
      this.materialString = materialString;
    }
    
    public void setEnchantments(HashMap<Enchantment, Integer> enchantments) {
      this.enchantments = enchantments;
    }
    
    public HashMap<Enchantment, Integer> getEnchantments() {
      return this.enchantments;
    }
    
    public PotionType getPotionType() {
      return this.potionType;
    }
    
    public void setPotionType(PotionType potionType) {
      this.potionType = potionType;
    }
    
    public boolean getPotionExtended() {
      return this.potionExtended;
    }
    
    public void setPotionExtended(boolean potionExtended) {
      this.potionExtended = potionExtended;
    }
    
    public boolean getPotionUpgraded() {
      return this.potionUpgraded;
    }
    
    public void setPotionUpgraded(boolean potionUpgraded) {
      this.potionUpgraded = potionUpgraded;
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
    
    public boolean shouldCheckMaterialContains() {
      return this.checkMaterialContains;
    }
    
    public void setCheckMaterialContains(boolean checkMaterialContains) {
      this.checkMaterialContains = checkMaterialContains;
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
    
    public boolean shouldCheckEnchanted() {
      return this.checkEnchanted;
    }
    
    public void setCheckEnchanted(boolean checkEnchanted) {
      this.checkEnchanted = checkEnchanted;
    }
    
    public boolean shouldCheckPotionType() {
      return this.checkPotionType;
    }
    
    public void setCheckPotionType(boolean checkPotionType) {
      this.checkPotionType = checkPotionType;
    }
    
    public boolean shouldCheckPotionExtended() {
      return this.checkPotionExtended;
    }
    
    public void setCheckPotionExtended(boolean checkPotionExtended) {
      this.checkPotionExtended = checkPotionExtended;
    }
    
    public boolean shouldCheckPotionUpgraded() {
      return this.checkPotionUpgraded;
    }
    
    public void setCheckPotionUpgraded(boolean checkPotionUpgraded) {
      this.checkPotionUpgraded = checkPotionUpgraded;
    }
    
    public void setRemove(boolean remove) {
      this.remove = remove;
    }
    
    public boolean shouldRemove() {
      return remove;
    }
    
  }
  
  public String onPlaceholderRequest(Player p, String args) {
    ItemWrapper wrapper = new ItemWrapper();
    ItemStack[] itemsToCheck;
    boolean amount = false;
    if (args.startsWith("amount_")) {
      args = args.replace("amount_", "");
      amount = true;
    }
    if (args.startsWith("remove_")) {
      wrapper.setRemove(true);
      args = args.replace("remove_", "");
    }
    wrapper = getWrapper(wrapper, ChatColor.translateAlternateColorCodes('&', args), p);
    
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
        itemsToCheck = new ItemStack[2];
        itemsToCheck[0] = p.getInventory().getItemInMainHand();
        itemsToCheck[1] = p.getInventory().getItemInOffHand();
      } catch (NoSuchMethodException e) {
        itemsToCheck = new ItemStack[1];
        itemsToCheck[0] = p.getInventory().getItemInHand();
      } catch (Exception e) {
        e.printStackTrace();
        return "error";
      }
    } else {
      itemsToCheck = p.getInventory().getContents();
    }
    if (amount) {
      return String.valueOf(getItemAmount(wrapper, p, itemsToCheck));
    } else {
      return checkItem(wrapper, p, itemsToCheck) ? PlaceholderAPIPlugin.booleanTrue()
          : PlaceholderAPIPlugin.booleanFalse();
    }
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
  
  private int getItemAmount(ItemWrapper wrapper, Player p, ItemStack... items) {
    int total = 0;
    List<ItemStack> matched = new ArrayList<ItemStack>();
    itemsLoop: for (ItemStack toCheck : items) {
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
          Map<Enchantment, Integer> toCheckEnchants;
          if (toCheckMeta instanceof EnchantmentStorageMeta) {
            toCheckEnchants = ((EnchantmentStorageMeta) toCheckMeta).getStoredEnchants();
          } else {
            toCheckEnchants = toCheckMeta.getEnchants();
          }
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
        if (wrapper.isStrict()) {
          remove = total == wrapper.getAmount();
        } else {
          remove = total >= wrapper.getAmount();
        }
      }
      if (remove) {
        ItemStack[] matchedArr = new ItemStack[matched.size()];
        if (wrapper.checkAmount) {
          int remaining = wrapper.getAmount();
          for (int i = 0; i < matched.size(); i++) {
            ItemStack item = matched.get(i);
            int match = p.getInventory().first(item);
            ItemStack matchedItem = p.getInventory().getItem(match);
            if (matchedItem.getAmount() > remaining) {
              matchedItem.setAmount(matchedItem.getAmount() - remaining);
              remaining = 0;
              break;
            } else {
              remaining -= matchedItem.getAmount();
              p.getInventory().remove(matchedItem);
            }
          }
        } else {
          p.getInventory().removeItem(matched.toArray(matchedArr));
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
  
  private ItemWrapper getWrapper(ItemWrapper wrapper, String input, Player p) {
    String[] arrayOfString;
    int j = (arrayOfString = input.split(",")).length;
    for (int i = 0; i < j; i++) {
      String part = arrayOfString[i];
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
                  .minecraft(PlaceholderAPI.setBracketPlaceholders(p, ench[0].toLowerCase()));
              enchantments.put(Enchantment.getByKey(key), Integer.valueOf(ench[1]));
            } else {
              NamespacedKey key = NamespacedKey.minecraft(PlaceholderAPI.setBracketPlaceholders(p, s.toLowerCase()));
              enchantments.put(Enchantment.getByKey(key), -1);
            }
          }
        } catch (NoSuchMethodException e) {
          for (String s : enchArray) {
            String[] ench;
            if ((ench = s.split("=")).length > 1) {
              enchantments.put(Enchantment.getByName(ench[0].toUpperCase()), Integer.valueOf(ench[1]));
            } else {
              enchantments.put(Enchantment.getByName(s.toUpperCase()), -1);
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
          wrapper.setPotionType(PotionType.valueOf(part));
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
      if (part.equals("inhand")) {
        wrapper.setCheckHand(true);
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
}
