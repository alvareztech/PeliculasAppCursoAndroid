package tech.alvarez.peliculasapp.services;

/**
 * Created on 11/26/16.
 *
 * @author Daniel Alvarez
 */
public class Actor {

    private String character;
    private String name;
    private String profile_path;

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfile_path() {
        return profile_path;
    }

    public void setProfile_path(String profile_path) {
        this.profile_path = profile_path;
    }
}
