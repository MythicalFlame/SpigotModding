package me.mythicalflame.spigotmodding.utilities;

import me.mythicalflame.spigotmodding.items.ModdedArmorSet;
import me.mythicalflame.spigotmodding.items.ModdedItem;

import java.util.ArrayList;

public final class Mod
{
    private final String namespace;
    private final String displayName;
    private final Version APIVersion;
    private final ArrayList<ModdedItem> registeredItems = new ArrayList<>();
    private final ArrayList<ModdedArmorSet> registeredArmor = new ArrayList<>();

    @SuppressWarnings("unused")
    public Mod(String namespace, String displayName, Version APIVersion)
    {
        this.namespace = namespace.toLowerCase();
        this.displayName = displayName;
        this.APIVersion = APIVersion;
    }

    public String getNamespace() { return namespace; }
    public String getDisplayName() { return displayName; }
    public Version getAPIVersion()
    {
        return APIVersion;
    }
    public ArrayList<ModdedItem> getRegisteredItems() { return registeredItems; }
    public ArrayList<ModdedArmorSet> getRegisteredArmor() { return registeredArmor; }

    @Override
    public String toString()
    {
        return displayName + " " + APIVersion;
    }

    @SuppressWarnings("unused")
    public void registerItem(ModdedItem item)
    {
        registeredItems.add(item);
    }

    @SuppressWarnings("unused")
    public void registerArmor(ModdedArmorSet set)
    {
        registeredArmor.add(set);
    }
}
