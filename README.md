# CheckItem-Expansion

**Placeholder:**
- `%checkitem_<modifier1>,<modifier2>,<...>%` - Returns if user has the item
- `%checkitem_amount_<modifier1>,<modifier2>,<...>%` - Returns amount of items the user has

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
- `strict`
- `inhand`

Ex: `%checkitem_mat:STONE,amt:1,data:2,nameequals:&6Test%`
