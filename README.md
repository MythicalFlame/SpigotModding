# SpigotModding Library
Very experimental and new, lacking many features.
## Usage
Add the release jar as a plugin on your server, and as a dependency for your new plugin.
### Custom Items
Currently, items can either be a regular item, a consumable, or a custom armor piece (custom armor covered in the next section).

For a regular item, extend the `ModdedItem` class and create a default constructor with a namespace, item id, material, and display name:
(Example for an item named TinSword)
```java
public ModdedItemTinSword()
{
  super("ultraswords", "tin_sword", Material.IRON_SWORD, "Tin Sword")
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
Custom armor is a bit more complex to set up. To begin, create your armor piece item classes. These should inherit from `ModdedArmorPiece`. Next, create a new class inheriting from `ArmorSetEffect`. The constructor should take potion effects. The plugin checks every 10 seconds if a player has an armor set on to give effects, so you should make the duration of all of your potion effects at least 10. Finally, create a subclass of `ModdedArmorSet` and pass in a `ModdedArmorPiece` array beginning with the helmet object and ending with your boots object, and an `ArmorSetEffect` object.

#### NOTE: if your armor set does not require some slots, put those slots as null in your `ModdedArmorPiece` array.
### Registering your mod
To register your custom items and armor sets, the `registerMods()` method can be used. This method takes a `ModdedItem` array (can be null to not register any) and a `ModdedArmorSet` array (can be null to not register any).
Registration example (with the steel armor set from above):
```java
import me.mythicalflame.spigotmodding.SpigotModding;
import me.mythicalflame.spigotmodding.items.ModdedItem;
import me.mythicalflame.spigotmodding.items.ModdedArmorSet;

...

ModdedItem[] items = {new ModdedItemSteelHelmet(), new ModdedItemSteelChestplate(), new ModdedItemSteelLeggings(), new ModdedItemSteelBoots()};
ModdedArmorSet[] sets = {new ModdedArmorSetSteelSet()};
SpigotModding.registerMods(items, sets);
```
### Features yet to be implemented
#### Tools with custom durability and potentially a weapons class (extending ModdedItem)
#### Custom Entities
Not supported yet, next priority.
#### Custom Blocks
Will take a lot of work, for now you can create a customItem for minimal functionality, but it will become a regular block when you place it.
