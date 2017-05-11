import java.util.ArrayList;
import java.util.List;
import org.sql2o.*;
import java.sql.Timestamp;
import java.util.Date;
import java.text.DateFormat;
import java.util.Timer;
import java.util.TimerTask;

public class WaterMonster extends Monster {

  public WaterMonster(String name, int personId) {
    this.name = name;
    this.personId = personId;
    playLevel = MAX_PLAY_LEVEL / 2;
    sleepLevel = MAX_SLEEP_LEVEL / 2;
    foodLevel = MAX_FOOD_LEVEL / 2;
    timer = new Timer();
  }
  public static List<WaterMonster> all() {
      String sql = "SELECT * FROM monsters;";
      try(Connection con = DB.sql2o.open()) {
          return con.createQuery(sql).executeAndFetch(WaterMonster.class);
      }
  }
  public static WaterMonster find(int id) {
      try(Connection con = DB.sql2o.open()) {
          String sql = "SELECT * FROM monsters where id=:id";
          WaterMonster monster = con.createQuery(sql)
          .addParameter("id",id)
          .executeAndFetchFirst(WaterMonster.class);
          return monster;
      }
  }

}
