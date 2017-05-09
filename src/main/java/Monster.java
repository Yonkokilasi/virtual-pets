import java.util.ArrayList;
import java.util.List;
import org.sql2o.*;

public class Monster {
    private String name;
    private int personId;
    private int id;
    private int foodLevel;
    private int sleepLevel;
    private int playLevel;

    public static final int MAX_FOOD_LEVEL = 3;
    public static final int MAX_SLEEP_LEVEL = 8;
    public static final int MAX_PLAY_LEVEL = 12;
    public static final int MIN_ALL_LEVELS = 0;

    public Monster(String name , int personId) {
        this.name = name;
        this.playLevel = MAX_PLAY_LEVEL / 2;
        this.personId = personId;
        this.sleepLevel = MAX_SLEEP_LEVEL / 2;
        this.foodLevel = MAX_FOOD_LEVEL / 2;
    }
    public int getFoodLevel(){
    return foodLevel;
  }
    public int getPlayLevel() {
        return playLevel;
    }
    public String getName(){
        return name;
    }
    public int getPersonId(){
        return personId;
    }
    public int getId(){
        return id;
    }
    public int getSleepLevel(){
        return sleepLevel;
    }

    @Override
    public boolean equals(Object otherMonster){
        if(!(otherMonster instanceof Monster)) {
            return false;
        } else {
            Monster newMonster = (Monster) otherMonster;
            return this.getName().equals(newMonster.getName()) && this.getPersonId() == newMonster.getPersonId();
        }
    }
    public void save() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO monsters (name,personid) VALUES (:name, :personid)";
            this.id = (int) con.createQuery(sql, true)
            .addParameter("name", this.name)
            .addParameter("personid",this.personId)
            .executeUpdate()
            .getKey();
        }
    }
    public static List<Monster> all() {
        String sql = "SELECT * FROM monsters";
        try(Connection con = DB.sql2o.open()) {
        return con.createQuery(sql).executeAndFetch(Monster.class);
        }
    }
    public static Monster find(int id) {
        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM monsters where id=:id";
            Monster monster = con.createQuery(sql)
            .addParameter("id",id)
            .executeAndFetchFirst(Monster.class);
            return monster;
        }
    }
    public boolean isAlive() {
        if (foodLevel <= MIN_ALL_LEVELS || playLevel <= MIN_ALL_LEVELS ||
        sleepLevel <= MIN_ALL_LEVELS) {
            return false;
        }
        return true;
    }
    public void depleteLevels(){
        playLevel--;
        foodLevel--;
        sleepLevel--;
    }
    public void play(){
        playLevel++;
    }

}
