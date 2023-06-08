package com.extendedclip.papi.expansion.checkitem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.logging.Level;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionType;

import de.tr7zw.changeme.nbtapi.NBTCompound;
import de.tr7zw.changeme.nbtapi.NBTItem;
import de.tr7zw.changeme.nbtapi.NBTType;
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
    return "2.7.0";
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
    private boolean checkNbtStrings;
    private boolean checkNbtInts;
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
    private Map<String, String> nbtStrings;
    private Map<String, Integer> nbtInts;
    
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
          + ", checkNbtStrings="
          + checkNbtStrings
          + ", checkNbtInts="
          + checkNbtInts
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
          + ", nbtStrings="
          + nbtStrings
          + ", nbtInts="
          + nbtInts
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
    
    public void setNbtStrings(Map<String, String> nbtStrings) {
      this.nbtStrings = nbtStrings;
    }
    
    public Map<String, String> getNbtStrings() {
      return this.nbtStrings;
    }
    
    public void setNbtInts(Map<String, Integer> nbtInts) {
      this.nbtInts = nbtInts;
    }
    
    public Map<String, Integer> getNbtInts() {
      return this.nbtInts;
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
    
    protected void setCheckNbtStrings(boolean checkNbtStrings) {
      this.checkNbtStrings = checkNbtStrings;
    }
    
    public boolean shouldCheckNbtStrings() {
      return this.checkNbtStrings;
    }
    
    protected void setCheckNbtInts(boolean checkNbtInts) {
      this.checkNbtInts = checkNbtInts;
    }
    
    public boolean shouldCheckNbtInts() {
      return this.checkNbtInts;
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
    if (p == null)
      return "%" + getIdentifier() + "_" + args + "%";
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
      boolean multiMod = false;
      if (argsSplit.length == 2) {
        wrapper = getWrapper(wrapper, ChatColor.translateAlternateColorCodes('&', argsSplit[1]), p);
        multiMod = argsSplit[1].split(",").length > 1;
      } else {
        wrapper.setCheckNameContains(true);
        wrapper.setCheckType(true);
        wrapper.setCheckAmount(true);
        wrapper.setCheckDurability(true);
        try {
          Class.forName("org.bukkit.inventory.meta.ItemMeta").getMethod("hasCustomModelData", null);
          wrapper.setCheckCustomData(true);
        } catch (Exception e) {
        }
        wrapper.setCheckLoreContains(true);
        wrapper.setCheckEnchantments(true);
        wrapper.setCheckEnchanted(true);
        wrapper.setCheckPotionType(true);
        wrapper.setCheckPotionExtended(true);
        wrapper.setCheckPotionUpgraded(true);
        wrapper.setCheckNbtStrings(true);
        wrapper = getWrapper(wrapper, "", p);
        multiMod = true;
      }
      ItemStack item = p.getInventory().getItem(slot);
      if (item == null) {
        return "";
      }
      String data = "";
      if ((wrapper.shouldCheckNameContains() || wrapper.shouldCheckNameEquals() || wrapper.shouldCheckNameStartsWith())
          && (item.hasItemMeta() && item.getItemMeta().hasDisplayName())) {
        data = multiMod ? data += "name:" : "";
        data += item.getItemMeta().getDisplayName() + " &r";
      }
      if (wrapper.shouldCheckType()) {
        data = multiMod ? data += "mat:" : "";
        data += item.getType() + " &r";
      }
      if (wrapper.shouldCheckAmount()) {
        data = multiMod ? data += "amt:" : "";
        data += item.getAmount() + " &r";
      }
      if (wrapper.shouldCheckDurability()) {
        data = multiMod ? data += "data:" : "";
        data += item.getDurability() + " &r";
      }
      if (wrapper.shouldCheckCustomData() && item.hasItemMeta() && item.getItemMeta().hasCustomModelData()) {
        data = multiMod ? data += "custommodeldata:" : "";
        data += item.getItemMeta().getCustomModelData() + " &r";
      }
      if ((wrapper.shouldCheckLoreContains() || wrapper.shouldCheckLoreEquals())
          && (item.hasItemMeta() && item.getItemMeta().hasLore())) {
        data = multiMod ? data += "lore:" : "";
        int line = -1;
        try {
          line = Integer.parseInt(wrapper.getLore());
        } catch (Exception e) {
        }
        if (line != -1) {
          data += item.getItemMeta().getLore().get(line);
        } else {
          for (String s : item.getItemMeta().getLore()) {
            data += s + "|";
          }
          data = data.substring(0, data.length() - 1);
        }
        data = data + " &r";
      }
      if (wrapper.shouldCheckEnchantments()) {
        if (!multiMod && wrapper.getEnchantments().size() == 1) {
          data = "0";
          if ((item.hasItemMeta() && item.getItemMeta().hasEnchants())
              || (item.hasItemMeta()
                  && (item.getItemMeta() instanceof EnchantmentStorageMeta)
                  && ((EnchantmentStorageMeta) item.getItemMeta()).hasStoredEnchants())) {
            Map<Enchantment, Integer> toCheckEnchants;
            if (item.getItemMeta() instanceof EnchantmentStorageMeta)
              toCheckEnchants = ((EnchantmentStorageMeta) item.getItemMeta()).getStoredEnchants();
            else
              toCheckEnchants = item.getItemMeta().getEnchants();
            for (Entry<Enchantment, Integer> e : wrapper.getEnchantments().entrySet()) {
              if (toCheckEnchants.containsKey(e.getKey())) {
                data = "" + toCheckEnchants.get(e.getKey());
                break;
              }
            }
          }
        } else if ((item.hasItemMeta() && item.getItemMeta().hasEnchants())
            || (item.hasItemMeta()
                && item.getItemMeta() instanceof EnchantmentStorageMeta
                && ((EnchantmentStorageMeta) item.getItemMeta()).hasStoredEnchants())) {
          data = multiMod ? data += "enchantments:" : "";
          Set<Entry<Enchantment, Integer>> enchantSet;
          if (item.getItemMeta() instanceof EnchantmentStorageMeta)
            enchantSet = ((EnchantmentStorageMeta) item.getItemMeta()).getStoredEnchants().entrySet();
          else
            enchantSet = item.getItemMeta().getEnchants().entrySet();
          for (Entry<Enchantment, Integer> entry : enchantSet) {
            data += entry.getKey().getKey() + ":" + entry.getValue() + "|";
          }
          data = data.substring(0, data.length() - 1) + " &r";
        }
      }
      if (wrapper.shouldCheckEnchanted() && item.hasItemMeta()) {
        data = multiMod ? data += "enchanted:" : "";
        data += (item.getItemMeta().hasEnchants()
            || (item.getItemMeta() instanceof EnchantmentStorageMeta
                && ((EnchantmentStorageMeta) item.getItemMeta()).hasStoredEnchants()))
            + " &r";
      }
      if (wrapper.shouldCheckPotionType() && item.hasItemMeta() && item.getItemMeta() instanceof PotionMeta) {
        data = multiMod ? data += "potiontype:" : "";
        PotionData potionData = ((PotionMeta) item.getItemMeta()).getBasePotionData();
        data += potionData.getType() + " &r";
      }
      if (wrapper.shouldCheckPotionExtended() && item.hasItemMeta() && item.getItemMeta() instanceof PotionMeta) {
        data = multiMod ? data += "potionextended:" : "";
        PotionData potionData = ((PotionMeta) item.getItemMeta()).getBasePotionData();
        data += potionData.isExtended() + " &r";
      }
      if (wrapper.shouldCheckPotionUpgraded() && item.hasItemMeta() && item.getItemMeta() instanceof PotionMeta) {
        data = multiMod ? data += "potionupgraded:" : "";
        PotionData potionData = ((PotionMeta) item.getItemMeta()).getBasePotionData();
        data += potionData.isUpgraded() + " &r";
      }
      if (wrapper.shouldCheckNbtStrings() || wrapper.shouldCheckNbtInts()) {
        NBTItem nbtItem = new NBTItem(item);
        int size = 0;
        if (wrapper.shouldCheckNbtStrings() && wrapper.getNbtStrings() != null)
          size += wrapper.getNbtStrings().size();
        if (wrapper.shouldCheckNbtInts() && wrapper.getNbtInts() != null)
          size += wrapper.getNbtInts().size();
        if (!multiMod && size == 1) {
          if (wrapper.shouldCheckNbtStrings()) {
            for (Entry<String, String> entry : wrapper.getNbtStrings().entrySet()) {
              if (entry.getKey().contains("..")) {
                String[] entrySplit = entry.getKey().split("\\.\\.");
                data = nbtItem.getCompound(entrySplit[0]).getString(entrySplit[1]);
              } else {
                data = nbtItem.getString(entry.getKey());
              }
            }
          } else {
            for (Entry<String, Integer> entry : wrapper.getNbtInts().entrySet()) {
              if (entry.getKey().contains("..")) {
                String[] entrySplit = entry.getKey().split("\\.\\.");
                data = "" + nbtItem.getCompound(entrySplit[0]).getInteger(entrySplit[1]);
              } else {
                data = "" + nbtItem.getInteger(entry.getKey());
              }
            }
          }
          
        } else if (!nbtItem.getKeys().isEmpty()) {
          data += "nbt:";
          for (String entry : nbtItem.getKeys()) {
            if (nbtItem.getType(entry).equals(NBTType.NBTTagString))
              data += "STRING:" + entry + ":" + nbtItem.getString(entry) + "|";
            else if (nbtItem.getType(entry).equals(NBTType.NBTTagInt))
              data += "INTEGER:" + entry + ":" + nbtItem.getInteger(entry) + "|";
            else if (nbtItem.getType(entry).equals(NBTType.NBTTagCompound)) {
              if (!entry.equalsIgnoreCase("display"))
                data += "NBTTagCompound:" + entry + ":" + nbtItem.getCompound(entry) + "|";
            }
          }
          data = data.endsWith("|") ? data.substring(0, data.length() - 1) + " &r" : data + " &r";
        }
      }
      return data.endsWith(" &r") ? data.substring(0, data.length() - 3) : data;
    }
    if (args.startsWith("amount_"))
    
    {
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
    wrapper =
        
        getWrapper(wrapper, ChatColor.translateAlternateColorCodes('&', args), p);
    
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
        if (amount) {
          int slots = 0;
          PlayerInventory inv = p.getInventory();
          for (ItemStack slot : inv.getContents()) {
            if (slot == null)
              slots++;
          }
          if (!Bukkit.getBukkitVersion().contains("1.7") && !Bukkit.getBukkitVersion().contains("1.8")) {
            if (inv.getItemInOffHand() == null || inv.getItemInOffHand().getType() == Material.AIR)
              slots--;
            if (inv.getHelmet() == null)
              slots--;
            if (inv.getChestplate() == null)
              slots--;
            if (inv.getLeggings() == null)
              slots--;
            if (inv.getBoots() == null)
              slots--;
          }
          return slots + "";
        }
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
    
    if (wrapper.shouldCheckNbtStrings() || wrapper.shouldCheckNbtInts()) {
      NBTItem nbtItem = new NBTItem(item);
      if (wrapper.shouldCheckNbtStrings()) {
        for (Entry<String, String> entry : wrapper.getNbtStrings().entrySet()) {
          if (entry.getKey().contains("..")) {
            String[] entrySplit = entry.getKey().split("\\.\\.");
            nbtItem.addCompound(entrySplit[0]).setString(entrySplit[1], entry.getValue());
          } else {
            nbtItem.setString(entry.getKey(), entry.getValue());
          }
        }
      }
      
      if (wrapper.shouldCheckNbtInts()) {
        for (Entry<String, Integer> entry : wrapper.getNbtInts().entrySet()) {
          
          if (entry.getKey().contains("..")) {
            String[] entrySplit = entry.getKey().split("\\.\\.");
            nbtItem.addCompound(entrySplit[0]).setInteger(entrySplit[1], entry.getValue());
          } else {
            nbtItem.setInteger(entry.getKey(), entry.getValue());
          }
        }
      }
      item = nbtItem.getItem();
    }
    
    if (wrapper.shouldCheckAmount()) {
      int remaining = wrapper.getAmount();
      int maxStack = item.getMaxStackSize();
      while (remaining > 0) {
        HashMap<Integer, ItemStack> returned;
        if (remaining > maxStack) {
          item.setAmount(maxStack);
          returned = p.getInventory().addItem(item);
          remaining -= maxStack;
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
          if (toCheckMeta.getEnchants().isEmpty()
              && (toCheckMeta instanceof EnchantmentStorageMeta
                  && ((EnchantmentStorageMeta) toCheckMeta).getStoredEnchants().isEmpty()))
            continue;
          Map<Enchantment, Integer> toCheckEnchants;
          if (toCheckMeta instanceof EnchantmentStorageMeta)
            toCheckEnchants = ((EnchantmentStorageMeta) toCheckMeta).getStoredEnchants();
          else
            toCheckEnchants = toCheckMeta.getEnchants();
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
        
        if (wrapper.shouldCheckNbtStrings() || wrapper.shouldCheckNbtInts()) {
          NBTItem nbtItem = new NBTItem(toCheck);
          if (wrapper.shouldCheckNbtStrings()) {
            for (Entry<String, String> entry : wrapper.getNbtStrings().entrySet()) {
              if (entry.getKey().contains("..")) {
                String[] entrySplit = entry.getKey().split("\\.\\.");
                if (nbtItem.getCompound(entrySplit[0]) == null) {
                  continue itemsLoop;
                }
                if (!checkNbtValue(entrySplit[1], entry.getValue(), nbtItem.getCompound(entrySplit[0]))) {
                  continue itemsLoop;
                }
              } else {
                if (!nbtItem.hasKey(entry.getKey())) {
                  continue itemsLoop;
                }
                if (!nbtItem.getString(entry.getKey()).equals(entry.getValue())) {
                  continue itemsLoop;
                }
              }
            }
          }
          if (wrapper.shouldCheckNbtInts()) {
            for (Entry<String, Integer> entry : wrapper.getNbtInts().entrySet()) {
              if (entry.getKey().contains("..")) {
                String[] entrySplit = entry.getKey().split("\\.\\.");
                if (nbtItem.getCompound(entrySplit[0]) == null) {
                  continue itemsLoop;
                }
                if (!checkNbtValue(entrySplit[1], entry.getValue(), nbtItem.getCompound(entrySplit[0]))) {
                  continue itemsLoop;
                }
              } else {
                if (!nbtItem.hasKey(entry.getKey())) {
                  continue itemsLoop;
                }
                if (!(nbtItem.getInteger(entry.getKey()) == entry.getValue())) {
                  continue itemsLoop;
                }
              }
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
        matched.add(toCheck);
      }
    }
    if (wrapper.shouldRemove())
    
    {
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
          
          try {
            Class.forName("org.bukkit.inventory.PlayerInventory").getMethod("getItemInOffHand", null);
            
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
            
          } catch (NoSuchMethodException e) {
            
          } catch (Exception e) {
            e.printStackTrace();
          }
          
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
          try {
            Class.forName("org.bukkit.inventory.PlayerInventory").getMethod("getItemInOffHand", null);
            
            ItemStack offhand = p.getInventory().getItemInOffHand();
            if (matched.contains(offhand)) {
              offhand = null;
            }
            p.getInventory().setItemInOffHand(offhand);
            
          } catch (NoSuchMethodException e) {
            
          } catch (Exception e) {
            e.printStackTrace();
          }
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
    input = input.replaceAll("__", " ");
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
        } catch (IllegalArgumentException e) {
          log(Level.WARNING, "Invalid Key for enchantment(s). -- Ignore if enchantment is blank on purpose");
          
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
      if (part.startsWith("nbtstrings:")) {
        part = part.replace("nbtstrings:", "");
        HashMap<String, String> nbtStrings = new HashMap<>();
        String[] nbtArray = part.split(";");
        for (String s : nbtArray) {
          String[] nbt;
          if ((nbt = s.split("=")).length > 1) {
            nbtStrings.put(PlaceholderAPI.setBracketPlaceholders(p, nbt[0]),
                PlaceholderAPI.setBracketPlaceholders(p, nbt[1]));
          }
        }
        wrapper.setNbtStrings(nbtStrings);
        wrapper.setCheckNbtStrings(true);
        continue;
      }
      if (part.startsWith("nbtints:")) {
        part = part.replace("nbtints:", "");
        HashMap<String, Integer> nbtInts = new HashMap<>();
        String[] nbtArray = part.split(";");
        for (String s : nbtArray) {
          String[] nbt;
          if ((nbt = s.split("=")).length > 1) {
            try {
              nbtInts.put(PlaceholderAPI.setBracketPlaceholders(p, nbt[0]),
                  Integer.parseInt(PlaceholderAPI.setBracketPlaceholders(p, nbt[1])));
            } catch (NumberFormatException e) {
            }
          }
        }
        wrapper.setNbtInts(nbtInts);
        wrapper.setCheckNbtInts(true);
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
  
  private boolean checkNbtValue(String key, String value, NBTCompound nbtCompound) {
    String[] keySplit = key.split("\\.\\.");
    if (keySplit.length > 1) {
      if (nbtCompound.getCompound(keySplit[0]) != null)
        return checkNbtValue(keySplit[1], value, nbtCompound.getCompound(keySplit[0]));
      return false;
    }
    String nbtValue = nbtCompound.getString(key);
    return nbtValue == null ? false : nbtValue.toString().equals(value);
  }
  
  private boolean checkNbtValue(String key, int value, NBTCompound nbtCompound) {
    String[] keySplit = key.split("\\.\\.");
    if (keySplit.length > 1) {
      if (nbtCompound.getCompound(keySplit[0]) != null)
        return checkNbtValue(keySplit[1], value, nbtCompound.getCompound(keySplit[0]));
      return false;
    }
    Integer nbtValue = nbtCompound.getInteger(key);
    return nbtValue == null ? false : nbtValue == value;
  }
}
