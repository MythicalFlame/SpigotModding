# SpigotModding Library
Very experimental and new, lacking many features.

## Usage
Add the release jar as a plugin on your server, and as a dependency for your new plugin.
### Warning
This version (the universal version) is intended for ancient Minecraft versions and will not be updated. If you are on 1.18 or newer, please use the latest version of the repository.

## Downloads
| Minecraft Version | Download Link                                                                | Supported? |
| ----------------- | ---------------------------------------------------------------------------- | ---------- |
| 1.18 and newer    | [check readme](https://github.com/MythicalFlame/SpigotModding)               | Depends    |
| 1.14.4-1.17.1     | [u1.0.0](https://github.com/MythicalFlame/SpigotModding/releases/tag/u1.0.0) | ❌         |

## Developer Guide
I'm working on replacing this with a wiki.

### Custom Items
Currently, items can either be a regular item, a consumable, or a custom armor piece (custom armor covered in the next section).

For a regular item, extend the `ModdedItem` class and create a default constructor with a namespace, item id, material, and display name:
(Example for an item named TinSword)
```java
public ModdedItemTinSword()
{
  super("ultraswords", "tin_sword", Material.IRON_SWORD, "Tin Sword");
}
```
Optionally, you can put an integer argument after the display name argument to add that number as custom model data for the item.

If you wish to make your custom item a consumable, the code is similar, but you need to extend `ModdedConsumable` and override the `onConsume(PlayerItemConsumeEvent event)` method

```java
@Override
public void onConsume(PlayerItemConsumeEvent event)
{
  Player player = event.getPlayer();
  player.addPotionEffect(new PotionEffect(INCREASE_DAMAGE, 60, 0), true);
}
```

Just as with regular items, custom consumables can also have a custom model data argument.

Custom tools and weapons do not have a class, so custom durability and damage does not exist
However, you can "create" damage by using events
```java
@Override
public void onAttack(EntityDamageByEntityEvent event)
{
  event.setDamage(...);
}
```

### Custom Armor
Custom armor is a bit more complex to set up. To begin, create your armor piece item classes. These should inherit from `ModdedArmorPiece`. Next, for each armor piece, create an `ArmorChoice` object. The constructor takes in an array of `ModdedArmorPiece` objects. An empty array means that no armor pieces are accepted for that slot while a null array means that all wearables are accepted. Finally, create a subclass of `ModdedArmorSet` and pass in an `ArmorChoice` array beginning with the helmet object and ending with your boots object. If you wish, you can override the `public void onTick(Player player)` method to do things like give resistance to wearers of the set.  
#### NOTE: if your armor set does not require some slots, put those slots as null in your `ModdedArmorPiece` array.

### Registering your mod
To register your custom items and armor sets, the `SpigotModding#registerMod` method can be used. This method takes a `Mod` object. Its constructor takes in a namespace, display name, and an API version. The Version class has two constructors: `int major, int minor, int patch, String releaseData` or `int major, int minor, int patch`, which uses "release" as the releaseData. For version u1.0.0, mods will be successfully registered if they are made with either `1.0.0-universal` or `0.7.0-release`.
Registration example (with the steel armor set from above):
```java
import me.mythicalflame.spigotmodding.SpigotModding;
import me.mythicalflame.spigotmodding.utilities.Mod;
import me.mythicalflame.spigotmodding.utilities.Version;
import me.mythicalflame.spigotmodding.items.ModdedItem;
import me.mythicalflame.spigotmodding.items.ModdedArmorSet;

...

Mod mod = new Mod("morearmors", "More Armors!", new Version(0, 7, 0));
mod.registerItem(new ModdedItemSteelHelmet());
mod.registerItem(new ModdedItemSteelChestplate());
mod.registerItem(new ModdedItemSteelLeggings());
mod.registerItem(new ModdedItemSteelBoots());
mod.registerArmor(new ModdedArmorSetSteelSet());
SpigotModding.registerMod(mod);
```

### Features and plans
This branch will not be updated with new features or minor bug fixes. It will only be updated in the event of a major plugin breakinf bug. For the latest updates and features, use the main branch (and an updated Minecraft version)
