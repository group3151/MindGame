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

    @Override
    public String toString() {
        return "Settings{" +
                "fileName='" + fileName + '\'' +
                ", levelDifficulty=" + levelDifficulty +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Settings settings = (Settings) o;

        if (levelDifficulty != settings.levelDifficulty) return false;

        return !(fileName != null ? !fileName.equals(settings.fileName) : settings.fileName != null);

    }

    @Override
    public int hashCode() {
        int result = fileName != null ? fileName.hashCode() : 0;
        result = 31 * result + levelDifficulty;
        return result;
    }

    public Settings() {
        Initialize();
    }

    public Settings(int levelDifficulty) {
        this.levelDifficulty = levelDifficulty;
    }

    private void Initialize() {

    }

    public int getDifficultyLevel() {
        return levelDifficulty;
    }

    public void setDifficultyLevel(int levelDifficulty) {
        this.levelDifficulty = levelDifficulty;
    }


    public int[] getLevelNumbers() {
        //1 - DotLevel
        //2 - MathLevel
        //3 - FigureLevel
        return new int[]{3};
    }

    public Level getLevel(int number, ImageView imageView) {
        switch (number) {
            case 1:
                return new DotLevel(imageView);
            case 2:
                return new MathLevel(imageView);
            case 3:
                return new ColorLevel(imageView);
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
    }

    private static class SettingsCreator implements Creator<Settings> {

        @Override
        public Settings createFromParcel(Parcel parcel) {
            int levelDifficulty = Integer.valueOf(parcel.readString());
            return new Settings(levelDifficulty);
        }

        @Override
        public Settings[] newArray(int size) {
            return new Settings[size];
        }
    }
}
