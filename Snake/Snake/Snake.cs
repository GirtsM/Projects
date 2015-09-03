using System;
using System.Drawing;
using System.Drawing.Drawing2D;
using System.Drawing.Text;
using System.Windows.Forms;
using System.IO;
using System.Collections.Generic;
using System.Data.SqlClient;

namespace Snake
{
    class Snake
    {
        public int gameWidth;
        public int gameHeight;
        public List<Point> snakePoints;
        public List<Point> wallPoints;
        public Point snakePoint;
        public Brush snakeColour;
        public Brush backColour;
        public Brush wallColour;
        public Point apple;
        public bool appleTest;
        public bool eated;
        public Brush appleColour;
        public int snakePiece;
        public int border;
        public int length;
        public int route;
        public int point;
        public int lvlPoints;
        public int waitTime;
        public Bitmap bmap;
        public Graphics grap;
        public int status;
        public int gameLvl;
        public int endLvl;
        public int IDuser;
        public FontFamily fontFamily;
        public Font someFont;

        public Snake(int id)
        {
            gameWidth = 600;
            gameHeight = 600;
            snakePoints = new List<Point>();
            wallPoints = new List<Point>();
            snakeColour = Brushes.Goldenrod;
            backColour = Brushes.Gray;
            wallColour = Brushes.Black;
            appleTest = false;
            appleColour = Brushes.Green;
            snakePiece = 20;
            border = 2;
            apple = new Point();
            apple.X = -1;
            apple.Y = -1;
            route = 2;
            point = 0;
            lvlPoints = 30;
            fontFamily = new FontFamily("Arial");
            someFont = new Font(fontFamily, 16, FontStyle.Regular, GraphicsUnit.Pixel);
            status = 1;
            gameLvl = 1;
            endLvl = 2;
            IDuser = id;
        }

        public void LoadGraphic()
        {
            bmap = new Bitmap(gameWidth, gameHeight);
            grap = Graphics.FromImage(bmap);
            grap.FillRectangle(backColour, 0, 0, gameWidth, gameHeight);
        }

        public void addSnakePoint(int x, int y)
        {
            grap.FillRectangle(snakeColour, x * snakePiece, y * snakePiece, snakePiece - border, snakePiece - border);
        }

        public void removeSnakePoint(int x, int y)
        {
            grap.FillRectangle(backColour, x * snakePiece, y * snakePiece, snakePiece, snakePiece);
        }

        public void snakeLeft()
        {
            snakePoint.X--;
        }

        public void snakeRight()
        {
            snakePoint.X++;
        }

        public void snakeUp()
        {
            snakePoint.Y--;
        }

        public void snakeDown()
        {
            snakePoint.Y++;
        }

        public bool appleCoord()
        {
            int vieta = 0;
            Point temp = snakePoints[vieta];

            for (int i = snakePoints.Count; i > 0; i--)
            {
                if ((apple.X == temp.X) && (apple.Y == temp.Y))
                {
                    return true;
                }
                else
                {
                    temp = snakePoints[vieta];
                    vieta++;
                }
            }
            int vieta2 = 0;
            if (wallPoints.Count > 0)
            {
                temp = wallPoints[vieta2];

                for (int j = wallPoints.Count; j > 0; j--)
                {
                    if ((apple.X == temp.X) && (apple.Y == temp.Y))
                    {
                        return true;
                    }
                    else
                    {
                        temp = wallPoints[vieta2];
                        vieta2++;
                    }
                }
            }
            return false;
        }

        public void generateApple()
        {
            Random random = new Random();
            apple.X = random.Next(1, gameWidth / snakePiece - 1);
            apple.Y = random.Next(1, gameHeight / snakePiece - 1);
            appleTest = true;
        }

        public void addApple(int x, int y)
        {
            if (!appleCoord())
            {
                grap.FillRectangle(appleColour, x * snakePiece, y * snakePiece, snakePiece - border, snakePiece - border);
            }
            else
            {
                appleTest = false;
            }
        }

        public bool isEaten()
        {
            if ((snakePoint.X == apple.X) && (snakePoint.Y == apple.Y))
            {
                return true;
            }
            else
            {
                return false;
            }
        }

        public bool eatSelf(int rout)
        {
            int x = snakePoint.X * snakePiece;
            int y = snakePoint.Y * snakePiece;
            try
            {
                if (rout == 1)
                {
                    if (bmap.GetPixel(x + snakePiece / 2, y + snakePiece / 2) == bmap.GetPixel(x + snakePiece / 2, y + snakePiece / 2 - snakePiece))
                    {
                        return true;
                    }

                    if (bmap.GetPixel(x + snakePiece / 2, y + snakePiece / 2 - snakePiece) == new Pen(wallColour).Color)
                    {
                        return true;
                    }
                }

                if (rout == 2)
                {
                    if (bmap.GetPixel(x + snakePiece / 2, y + snakePiece / 2) == bmap.GetPixel(x + snakePiece / 2 + snakePiece, y + snakePiece / 2))
                    {
                        return true;
                    }

                    if (bmap.GetPixel(x + snakePiece / 2 + snakePiece, y + snakePiece / 2) == new Pen(wallColour).Color)
                    {
                        return true;
                    }
                }

                if (rout == 3)
                {
                    if (bmap.GetPixel(x + snakePiece / 2, y + snakePiece / 2) == bmap.GetPixel(x + snakePiece / 2, y + snakePiece / 2 + snakePiece))
                    {
                        return true;
                    }

                    if (bmap.GetPixel(x + snakePiece / 2, y + snakePiece / 2 + snakePiece) == new Pen(wallColour).Color)
                    {
                        return true;
                    }
                }

                if (rout == 4)
                {
                    if (bmap.GetPixel(x + snakePiece / 2, y + snakePiece / 2) == bmap.GetPixel(x + snakePiece / 2 - snakePiece, y + snakePiece / 2))
                    {
                        return true;
                    }

                    if (bmap.GetPixel(x + snakePiece / 2 - snakePiece, y + snakePiece / 2) == new Pen(wallColour).Color)
                    {
                        return true;
                    }
                }
            }
            catch (Exception ex)
            {
                return false;
            }

            return false;
        }

        public void placeBorder(int x1, int y1, int x2, int y2)
        {
            if (x1 == x2)
            {
                while (y1 <= y2)
                {
                    grap.FillRectangle(wallColour, x1 * snakePiece, y1 * snakePiece, snakePiece, snakePiece);
                    Point temp = new Point();
                    temp.X = x1;
                    temp.Y = y1;
                    wallPoints.Add(temp);
                    y1++;
                }
            }

            else if (y1 == y2)
            {
                while (x1 <= x2)
                {
                    grap.FillRectangle(wallColour, x1 * snakePiece, y1 * snakePiece, snakePiece, snakePiece);
                    Point temp = new Point();
                    temp.X = x1;
                    temp.Y = y1;
                    wallPoints.Add(temp);
                    x1++;
                }
            }
        }

        public void lvlClear()
        {
            Font temp = new Font(fontFamily, 20, FontStyle.Regular);
            grap.FillRectangle(appleColour, 200, 200, 230, 30);
            grap.DrawString("Lvl Clear", temp, snakeColour, 200, 200);

        }

        public void gameDone()
        {
            string connection = string.Format("Data Source=PHO-PC;Initial Catalog=game;User ID={0};pwd={1};", "sa", "parole");
            SqlConnection con = new SqlConnection(connection);
            SqlCommand command = con.CreateCommand();
            int id_user = IDuser;
            int score = 0;
            for (int i = 1; i < gameLvl; i++)
            {
                score += lvlPoints;
            }
            score += point;
            int lvl = gameLvl;
            command.CommandText = "INSERT INTO dbo.SnakeGame (ID_user, score, lvl) VALUES('" + id_user + "','" + score + "','" + lvl + "')";
            con.Open();
            command.ExecuteNonQuery();
            con.Close();

            Font temp = new Font(fontFamily, 20, FontStyle.Regular);
            grap.FillRectangle(appleColour, 180, 200, 270, 30);
            grap.DrawString("YOU ARE THE BEST", temp, snakeColour, 180, 200);

        }

        public void gameOver()
        {
            string connection = string.Format("Data Source=PHO-PC;Initial Catalog=game;User ID={0};pwd={1};", "sa", "parole");
            SqlConnection con = new SqlConnection(connection);
            SqlCommand command = con.CreateCommand();
            int id_user = IDuser;
            int score = 0;
            for (int i = 1; i < gameLvl; i++)
            {
                score += lvlPoints;
            }
            score += point;
            int lvl = gameLvl;
            command.CommandText = "INSERT INTO dbo.SnakeGame (ID_user, score, lvl) VALUES('" + id_user + "','" + score + "','" + lvl + "')";
            con.Open();
            command.ExecuteNonQuery();
            con.Close();

            Font temp = new Font(fontFamily, 20, FontStyle.Regular);
            grap.FillRectangle(appleColour, 200, 200, 175, 30);
            grap.DrawString("Game Over", temp, snakeColour, 200, 200);
        }
    }
}
