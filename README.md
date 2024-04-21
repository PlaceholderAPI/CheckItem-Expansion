# CheckItem-Expansion

**Placeholder:**
- `%checkitem_<modifier1>,<modifier2>,<...>%` - Returns if user has the item
- `%checkitem_amount_<modifier1>,<modifier2>,<...>%` - Returns amount of items the user has
- `%checkitem_remove_<modifier1>,<modifier2>,<...>%` - Removes the items from the players inventory - Can be used with amount, it just has to be after. (Ex. `%checkitem_amount_remove_<...>%`)
- `%checkitem_give_<modifier1>,<modifier2>,<...>%` - Gives the player an item. Returns yes if successful, returns amount of items NOT given if unsuccessful.
- `%checkitem_getinfo:<slot>_<modifier1>,<modifier2>,<...>%` - Returns information about an item in a slot. Returns information in the same order listed on this wiki. List is seperated via ` &r` (Ex. `%checkitem_getinfo:0_mat:`)

*Notes:*  
*- `mainhand` and `offhand` work in `getinfo:<slot>`*  
*- `give` and `remove` placeholders are **DISABLED** by default. See PlaceholderAPI `config.yml` file to enable.*

**Modifiers:**
- `namecontains:<string>`~
- `namestartswith:<string>`~
- `nameequals:<string>`*~
- `mat:<material>`*~
- `amt:<integer>`*~
- `data:<integer>`*~
- `custommodeldata:<integer>`*~
- `lorecontains:<string>`~
- `loreequals:<string>` (Separate lines with `|`)*~
- `matcontains:<string>`
- `enchantments:<enchantment=lvl>;<enchantment>` (`=lvl` is optional)*~
- `enchanted`~
- `potiontype:<potiontype>`*~
- `potionextended:<boolean>`*`
- `potionupgraded:<boolean>`*~
- `strict`
- `inhand<:hand>` (`inhand` will check both hands, you can add `:main` or `:off` to check specific hands)
- `inslot:<integer>` [Valid Slots](https://i.imgur.com/3YCrfC8.png)
- `nbtstrings:<string>=<string>;<string>=<string>`*~
- `nbtints:<string>=<integer>;<string>=<integer>`*~

*For nbt data you can use compounds by putting `..` inside your string Example (`%checkitem_nbtstrings:PublicBukkitValues..executableitems:ei-id=Free_Money%`)**


Ex: `%checkitem_mat:STONE,amt:1,data:2,nameequals:&6Test%`

To use commas in strings, escape them with `\` (Ex: `nameequals:This\, is a test`)


To use semicolon in lists, escape them with `\` (Ex: `nbtints:ae_enchantment\;mirror=1`)

**PAPI Placeholders work, you just need to put them in `{}` instead of `%%`**

*Works with `give` placeholder.

~Works with the `getinfo` placeholder
