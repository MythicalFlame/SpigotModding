package me.mythicalflame.netherreactor.utilities;

import me.mythicalflame.netherreactor.items.ModdedArmorSet;
import me.mythicalflame.netherreactor.items.ModdedItem;

import java.util.ArrayList;

/**
 * Represents a game modification.
 */
public final class Mod
{
    /**
     * The namespace that the mod belongs to.
     */
    private final String namespace;
    /**
     * The display name of the mod.
     */
    private final String displayName;
    /**
     * The API version that this mod is based off of.
     */
    private final Version APIVersion;
    /**
     * The custom items that the mod uses.
     */
    private final ArrayList<ModdedItem> registeredItems = new ArrayList<>();
    /**
     * The armor sets that the mod uses.
     */
    private final ArrayList<ModdedArmorSet> registeredArmor = new ArrayList<>();

    /**
     * Constructs a mod.
     *
     * @param namespace The namespace that this mod belongs to.
     * @param displayName The display name of this mod.
     * @param APIVersion The API version that this mod is based off of.
     */
    @SuppressWarnings("unused")
    public Mod(String namespace, String displayName, Version APIVersion)
    {
        this.namespace = namespace.toLowerCase();
        this.displayName = displayName;
        this.APIVersion = APIVersion;
    }

    /**
     * @return The namespace that this mod belongs to.
     */
    public String getNamespace() { return namespace; }

    /**
     * @return The display name of this mod.
     */
    public String getDisplayName() { return displayName; }

    /**
     * @return The API version this mod is based off of.
     */
    public Version getAPIVersion()
    {
        return APIVersion;
    }

    /**
     * @return An ArrayList of the ModdedItems registered in this mod.
     */
    public ArrayList<ModdedItem> getRegisteredItems() { return registeredItems; }

    /**
     * @return An ArrayList of the ModdedArmorSets registered in this mod.
     */
    public ArrayList<ModdedArmorSet> getRegisteredArmor() { return registeredArmor; }

    @Override
    public String toString()
    {
        return displayName;
    }

    /**
     * Registers a custom item with the mod.
     *
     * @param item The custom item to register.
     */
    public void registerItem(ModdedItem item)
    {
        registeredItems.add(item);
    }

    /**
     * Registers an armor set with the mod.
     *
     * @param set The armor set to register.
     */
    public void registerArmor(ModdedArmorSet set)
    {
        registeredArmor.add(set);
    }
}
