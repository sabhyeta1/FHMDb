package at.ac.fhcampuswien.fhmdb.database;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "WatchList")
public class WatchlistMovieEntity {
    @DatabaseField(generatedId = true)
    private long id;
  //  @DatabaseField(foreign = true,columnName = "movie_id")
   // private MovieEntity movie;
   @DatabaseField()
   private String apiId;

    public WatchlistMovieEntity(){}

    public WatchlistMovieEntity(String apiId) {
        this.apiId = apiId;
    }

    public String getApiId() {
        return apiId;
    }

    public void setApiId(String apiId) {
        this.apiId = apiId;
    }

}
