package reservation.server;
import java.io.Serializable;

public class Movie implements Serializable{
    private static final long serialVersionUID = 1L; //Version control
    private String name;
    private String description;
    private String timings;

    public Movie(String name, String description, String timings)
    {
        this.name = name;
        this.description = description;
        this.timings = timings;
    }

    //Getter
    public String getName()
    {
        return name;
    }

    public String getDescription()
    {
        return description;
    }

    public String getTimings()
    {
        return timings;
    }

    @Override
    public String toString()
    {
        return "Movie{" + "name = " + name + '\'' + ", description = " + description + '\'' + ", timings = " + timings+'}';
    }
}
