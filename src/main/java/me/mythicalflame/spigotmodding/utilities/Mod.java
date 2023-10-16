package me.mythicalflame.spigotmodding.utilities;

import me.mythicalflame.spigotmodding.items.ModdedArmorSet;
import me.mythicalflame.spigotmodding.items.ModdedItem;

import java.util.ArrayList;

public final class Mod
{
    private final String namespace;
    private final String displayName;
    private final ArrayList<ModdedItem> registeredItems = new ArrayList<>();
    private final ArrayList<ModdedArmorSet> registeredArmor = new ArrayList<>();

    public Mod(String namespace, String displayName)
    {
        this.namespace = namespace.toLowerCase();
        this.displayName = displayName;
    }

    public String getNamespace() { return namespace; }
    public String getDisplayName() { return displayName; }
    public ArrayList<ModdedItem> getRegisteredItems() { return registeredItems; }
    public ArrayList<ModdedArmorSet> getRegisteredArmor() { return registeredArmor; }

    public void registerItem(ModdedItem item)
    {
        registeredItems.add(item);
    }
}
