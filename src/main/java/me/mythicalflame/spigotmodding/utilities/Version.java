package me.mythicalflame.spigotmodding.utilities;

public class Version
{
    private final int major;
    private final int minor;
    private final int patch;
    private final String releaseData;

    @SuppressWarnings("unused")
    public Version(int major, int minor, int patch)
    {
        this.major = major;
        this.minor = minor;
        this.patch = patch;
        this.releaseData = "release";
    }

    public Version(int major, int minor, int patch, String releaseData)
    {
        this.major = major;
        this.minor = minor;
        this.patch = patch;
        this.releaseData = releaseData;
    }

    @SuppressWarnings("unused")
    public int getMajor()
    {
        return major;
    }

    @SuppressWarnings("unused")
    public int getMinor()
    {
        return minor;
    }

    @SuppressWarnings("unused")
    public int getPatch()
    {
        return patch;
    }

    @SuppressWarnings("unused")
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
        if (other == null)
        {
            return false;
        }

        if (!(other instanceof Version otherVersion))
        {
            return false;
        }

        return major == otherVersion.major && minor == otherVersion.minor && patch == otherVersion.patch && releaseData.equals(otherVersion.releaseData);
    }
}
