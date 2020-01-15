# CheckItem-Expansion

**Placeholder:**
- `%checkitem_<modifier1>,<modifier2>,<...>%` - Returns if user has the item
- `%checkitem_amount_<modifier1>,<modifier2>,<...>%` - Returns amount of items the user has

**Modifiers:**
- `namecontains:<name>`
- `namestartswith:<name>`
- `nameequals:<name>`
- `mat:<material>`
- `amt:<amount>`
- `data:<data>`
- `lorecontains:<lore>`
- `enchantments:<enchantment=lvl>;<enchantment>` (=lvl is optional)
- `strict`
- `inhand`

Ex: `%checkitem_mat:STONE,amt:1,data:2,nameequals:&6Test%`
