# CheckItem-Expansion

**Placeholder:**
- `%checkitem_<modifier1>,<modifier2>,<...>%` - Returns if user has the item
- `%checkitem_amount_<modifier1>,<modifier2>,<...>%` - Returns amount of items the user has
- `%checkitem_remove_<modifier1>,<modifier2>,<...>%` - Removes the items from the players inventory - Can be used with amount, it just has to be after. (Ex. `%checkitem_amount_remove_<...>%`)

**Modifiers:**
- `namecontains:<string>`
- `namestartswith:<string>`
- `nameequals:<string>`
- `mat:<material>`
- `amt:<integer>`
- `data:<integer>`
- `custommodeldata:<integer>`
- `lorecontains:<string>`
- `matcontains:<string>`
- `enchantments:<enchantment=lvl>;<enchantment>` (`=lvl` is optional)
- `enchanted`
- `potiontype:<potiontype>`
- `potionextended`
- `potionupgraded`
- `strict`
- `inhand`

Ex: `%checkitem_mat:STONE,amt:1,data:2,nameequals:&6Test%`

**PAPI Placeholders work, you just need to put them in `{}` instead of `%%`**