# SpigotModding Library
Very experimental and new, lacking many features.
## Usage
As this project is very basic and new so far, I have hardcoded some items for testing purposes.
To register custom items, you will need to program them in the CommandTest command, or create another way of giving them.

### Custom Items
Extend `ModdedItem` class, and create a default constructor:
(Example for an item named TinSword)
```java
public ModdedItemTinSword()
{
  super(Material.STONE_SWORD, "Tin Sword")
}
```
CustomModelData is not yet supported, but will be soon.

Additionally, custom tools and weapons do not have a class, so custom durability and damage does not exist
However, you can "create" damage by using events
```java
public void onLeftClick()
{
  if (/*hit mob*/)
  {
    //Deal x damage to mob
  }
}
```
### Custom Entities
Not supported yet, next priority. Initial implementations will likely use a vanilla entity rather than NMS
### Custom Blocks
Will take a lot of work, for now you can create a customItem for minimal functionality, but it will become a regular block when you place it.
