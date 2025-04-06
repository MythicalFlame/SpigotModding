package me.mythicalflame.netherreactor.utilities;

import java.util.Arrays;

/**
 * This class represents a NetherReactor API version.
 */
public class Version
{
    /**
     * The major version number.
     */
    private final int major;
    /**
     * The minor version number.
     */
    private final int minor;
    /**
     * The patch version number.
     */
    private final int patch;
    /**
     * The release metadata.
     */
    private final String releaseData;

    /**
     * Constructs a Version object with the release metadata "release".
     *
     * @param major The major version number.
     * @param minor The minor version number.
     * @param patch The patch version number.
     */
    public Version(int major, int minor, int patch)
    {
        this.major = major;
        this.minor = minor;
        this.patch = patch;
        this.releaseData = "release";
    }

    /**
     * @param major The major version number.
     * @param minor The minor version number.
     * @param patch The patch version number.
     * @param releaseData The release metadata.
     */
    public Version(int major, int minor, int patch, String releaseData)
    {
        this.major = major;
        this.minor = minor;
        this.patch = patch;
        this.releaseData = releaseData;
    }

    /**
     * @return The major version number.
     */
    public int getMajor()
    {
        return major;
    }

    /**
     * @return The minor version number.
     */
    public int getMinor()
    {
        return minor;
    }

    /**
     * @return The patch version number.
     */
    public int getPatch()
    {
        return patch;
    }

    /**
     * @return The release metadata.
     */
    public String getReleaseData()
    {
        return releaseData;
    }

    @Override
    public String toString()
    {
        return "v" + major + "." + minor + "." + patch + "-" + releaseData;
    }

    @Override
    public boolean equals(Object other)
    {
        if (!(other instanceof Version otherVersion))
        {
            return false;
        }

        return major == otherVersion.major && minor == otherVersion.minor && patch == otherVersion.patch && releaseData.equals(otherVersion.releaseData);
    }

    @Override
    public int hashCode()
    {
        return Arrays.hashCode(new Object[]{major, minor, patch, releaseData});
    }
}
