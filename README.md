# SpigotModding Library
Very experimental and new, lacking many features.
## Usage
Add the release jar as a plugin on your server, and as a dependency for your new plugin.
### Custom Items
Extend `ModdedItem` class, and create a default constructor with a namespace, item id, material, and display name:
(Example for an item named TinSword)
```java
public ModdedItemTinSword()
{
  super("ultraswords", "tin_sword", Material.IRON_SWORD, "Tin Sword")
}
```
CustomModelData is not yet supported, but will be soon.

Additionally, custom tools and weapons do not have a class, so custom durability and damage does not exist
However, you can "create" damage by using events
```java
@Override
public void onLeftClick()
{
  if (/*hit mob*/)
  {
    //Deal x damage to mob
  }
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
#### Food and tools/weapons class (extending ModdedItem)
#### Custom Entities
Not supported yet, next priority.
#### Custom Blocks
Will take a lot of work, for now you can create a customItem for minimal functionality, but it will become a regular block when you place it.
