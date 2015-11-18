package game.MindGame;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;


/**
 * Created by DVitinnik on 20-Oct-15.
 */
public class Settings implements Parcelable {
    public static final Creator<Settings> CREATOR = new SettingsCreator();

    String fileName = "settings.xml";
    int levelDifficulty;
    int soundVolume;
    int musicVolume;

    @Override
    public String toString() {
        return "Settings{" +
                "fileName='" + fileName + '\'' +
                ", levelDifficulty=" + levelDifficulty +
                ", soundVolume=" + soundVolume +
                ", musicVolume=" + musicVolume +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Settings settings = (Settings) o;

        if (levelDifficulty != settings.levelDifficulty) return false;
        if (soundVolume != settings.soundVolume) return false;
        if (musicVolume != settings.musicVolume) return false;
        return !(fileName != null ? !fileName.equals(settings.fileName) : settings.fileName != null);

    }

    @Override
    public int hashCode() {
        int result = fileName != null ? fileName.hashCode() : 0;
        result = 31 * result + levelDifficulty;
        result = 31 * result + soundVolume;
        result = 31 * result + musicVolume;
        return result;
    }

    public Settings() {
        Initialize();
    }

    public Settings(int levelDifficulty, int soundVolume, int musicVolume) {
        this.levelDifficulty = levelDifficulty;
        this.soundVolume = soundVolume;
        this.musicVolume = musicVolume;
    }

    private void Initialize() {

    }

    public int getDifficultyLevel() {
        return levelDifficulty;
    }

    public void setDifficultyLevel(int levelDifficulty) {
        this.levelDifficulty = levelDifficulty;
    }

    public int getSoundVolume() {
        return soundVolume;
    }

    public void setSoundVolume(int soundVolume) {
        this.soundVolume = soundVolume;
    }

    public int getMusicVolume() {
        return musicVolume;
    }

    public void setMusicVolume(int musicVolume) {
        this.musicVolume = musicVolume;
    }

    public int[] getLevelNumbers() {
        //1 - DotLevel
        //2 - MathLevel
        //3 - FigureLevel
        return new int[]{1};
    }

    public Level getLevel(int number, ImageView imageView) {
        switch (number) {
            case 1:
                return new DotLevel(imageView);
            case 2:
                return new DotLevel(imageView);
            case 3:
                return new DotLevel(imageView);
            default:
                return null;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(String.valueOf(levelDifficulty));
        parcel.writeString(String.valueOf(soundVolume));
        parcel.writeString(String.valueOf(musicVolume));
    }

    private static class SettingsCreator implements Creator<Settings> {

        @Override
        public Settings createFromParcel(Parcel parcel) {
            int levelDifficulty = Integer.valueOf(parcel.readString());
            int soundVolume = Integer.valueOf(parcel.readString());
            int musicVolume = Integer.valueOf(parcel.readString());
            return new Settings(levelDifficulty, soundVolume, musicVolume);
        }

        @Override
        public Settings[] newArray(int size) {
            return new Settings[size];
        }
    }
}
