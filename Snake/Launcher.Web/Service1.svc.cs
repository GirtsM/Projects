using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.Text;

namespace Launcher.Web
{
    // NOTE: You can use the "Rename" command on the "Refactor" menu to change the class name "Service1" in code, svc and config file together.
    // NOTE: In order to launch WCF Test Client for testing this service, please select Service1.svc or Service1.svc.cs at the Solution Explorer and start debugging.
    public class Service1 : IService1
    {
        public void DoWork()
        {
        }

        public List<User> getUsernames()
        {
            gameEntities3 db = new gameEntities3();
            var rez = from item in db.User.AsEnumerable()
                      select item;

            List<User> users = rez.ToList();
            return users;
        }

        public void addUser(User u)
        {
            gameEntities3 db = new gameEntities3();
            db.User.Add(u);
            db.SaveChanges();
        }

        public List<SnakeGame> getSnakeScoreData(int id)
        {
            gameEntities3 db = new gameEntities3();
            var rez = from item in db.SnakeGame.AsEnumerable()
                      where item.ID_user == id
                      orderby item.score descending
                      select item;

            List<SnakeGame> scores = rez.ToList();
            return scores;
        }

        public List<ShooterGame> getShooterScoreData(int id)
        {
            gameEntities3 db = new gameEntities3();
            var rez = from item in db.ShooterGame.AsEnumerable()
                      where item.ID_user == id
                      orderby item.score descending
                      select item;

            List<ShooterGame> scores = rez.ToList();
            return scores;
        }

        public List<SnakeGame> getSnakeRecordData()
        {
            gameEntities3 db = new gameEntities3();
            var rez = from item in db.SnakeGame.AsEnumerable()
                      orderby item.score descending
                      select item;

            List<SnakeGame> scores = rez.ToList();
            return scores;
        }

        public List<ShooterGame> getShooterRecordData()
        {
            gameEntities3 db = new gameEntities3();
            var rez = from item in db.ShooterGame.AsEnumerable()
                      orderby item.score descending
                      select item;

            List<ShooterGame> scores = rez.ToList();
            return scores;
        }
    }
}
