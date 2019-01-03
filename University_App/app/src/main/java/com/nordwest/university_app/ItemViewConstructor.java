package com.nordwest.university_app;

public class ItemViewConstructor {
    /*This is a constructor class for floor plan cards used by the adaptor in recyclerView to display
     * each class of a specific group */
    int background;
    String profileName;
    int profilePhoto;
    String floorDescription;

    //empty constructor when no data is set
    public ItemViewConstructor() {
    }
    //constructor that requires four parameters
    public ItemViewConstructor(int background, String profileName, int profilePhoto, String nbFollowers) {
        this.background = background;
        this.profileName = profileName;
        this.profilePhoto = profilePhoto;
        this.floorDescription = nbFollowers;
    }

    //setters and getters
    public int getBackground() {
        return background;
    }

    public void setBackground(int background) {
        this.background = background;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public int getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(int profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    public String getFloorDescription() {
        return floorDescription;
    }

    public void setFloorDescription(String floorDescription) {
        this.floorDescription = floorDescription;
    }
}
