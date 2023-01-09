# SpigotModding Library
Very experimental and new, lacking many features.
## Usage
Add the release jar as a plugin on your server, and as a dependency for your new plugin.
### Custom Items
Currently, items can either be a regular item or a consumable.

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
### Registering items
In the startup logic section of your plugin, you need to register items. Items that you register MUST have the same namespace. If you wish to use separate namespaces, create a separate plugin.
Registering example (with the tin sword from above):
```java
import me.mythicalflame.spigotmodding.SpigotModding;
import me.mythicalflame.spigotmodding.items.ModdedItem;

...

ModdedItem[] items = {new ModdedItemTinSword()};
SpigotModding.registerItems(items);
```
### Features yet to be implemented
#### Tools with custom durability and potentially a weapons class (extending ModdedItem)
#### Custom Entities
Not supported yet, next priority.
#### Custom Blocks
Will take a lot of work, for now you can create a customItem for minimal functionality, but it will become a regular block when you place it.
