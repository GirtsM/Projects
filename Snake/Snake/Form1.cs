using System;
using System.Collections.Generic;
using System.Drawing;
using System.Windows.Forms;
using System.Threading;
using System.Data.SqlClient;

namespace Snake
{
    public partial class Form1 : Form
    {
        public int user_ID;
        Snake game;
        public Form1()
        {
            InitializeComponent();
        }
        void FormLoad(object sender, EventArgs e)
        {
            game = new Snake(user_ID);
            points.Font = new Font(game.fontFamily, 12, FontStyle.Regular);
            level.Font = new Font(game.fontFamily, 12, FontStyle.Regular);
            lvlMap();
            setup();

        }

        void setup()
        {
            for (int i = game.length; i > 0; i--)
            {
                game.snakePoints.Add(game.snakePoint);
                game.addSnakePoint(game.snakePoint.X, game.snakePoint.Y);
                game.snakePoint.X++;
            }
            game.snakePoint.X--;
        }

        void gameTimerTick(object sender, EventArgs e)
        {
            playing();
        }

        public bool playing()
        {
            level.Text = "LVL-" + game.gameLvl.ToString();
            points.Text = game.point.ToString() + "/" + game.lvlPoints.ToString();

            if (game.point >= game.lvlPoints)
            {
                clearLvl();
                return true;
            }

            if (game.eatSelf(game.route))
            {
                gameTimer.Enabled = false;
                waitTimer.Enabled = true;
                game.waitTime = 20;
                return true;

            }

            if (game.route == 1)
            {
                game.snakeUp();
            }
            else if (game.route == 2)
            {
                game.snakeRight();
            }
            else if (game.route == 3)
            {
                game.snakeDown();
            }
            else if (game.route == 4)
            {
                game.snakeLeft();
            }

            game.addSnakePoint(game.snakePoint.X, game.snakePoint.Y);
            game.snakePoints.Add(game.snakePoint);

            if (!game.eated)
            {
                game.removeSnakePoint(game.snakePoints[0].X, game.snakePoints[0].Y);
                game.snakePoints.RemoveAt(0);
            }
            else
            {
                game.eated = false;
            }

            if (!game.appleTest)
            {
                game.generateApple();
                if (!game.appleCoord())
                {
                    game.addApple(game.apple.X, game.apple.Y);
                }
            }

            if (game.isEaten())
            {
                game.generateApple();
                if (!game.appleCoord())
                {
                    game.addApple(game.apple.X, game.apple.Y);
                }

                else
                {
                    game.appleTest = false;
                }
                game.eated = true;
                game.point += 10;
                if (gameTimer.Interval > 40)
                {
                    gameTimer.Interval--;
                }
            }

            pictureBox.Image = game.bmap;
            pictureBox.Refresh();
            return true;
        }

        void gameKeys(object sender, KeyEventArgs e)
        {
            if (game.status == 2)
            {
                if (e.KeyCode == Keys.Up)
                {
                    game.route = 1;
                }
                else if (e.KeyCode == Keys.Right)
                {
                    game.route = 2;
                }
                else if (e.KeyCode == Keys.Down)
                {
                    game.route = 3;
                }
                else if (e.KeyCode == Keys.Left)
                {
                    game.route = 4;
                }

                game.waitTime = 10;
                gameTimer.Enabled = true;
                waitTimer.Enabled = false;
            }
        }

        private void button1_Click(object sender, EventArgs e)
        {
            game.IDuser = user_ID;
            game.status = 2;
            game.route = 2;
            gameTimer.Enabled = true;
            panel1.Visible = false;
            this.Focus();

        }

        private void button2_Click(object sender, EventArgs e)
        {
            Application.Exit();
        }

        void gameOver()
        {
            game.gameOver();
            pictureBox.Refresh();
            panel2.Visible = true;

        }


        void waitTimerTick(object sender, EventArgs e)
        {
            game.waitTime--;
            if (game.waitTime == 0)
            {
                waitTimer.Enabled = false;
                gameOver();
            }
        }

        public void lvlMap()
        {
            int temp, temp2;
            game.LoadGraphic();
            game.snakePoint.X = 5;
            game.snakePoint.Y = 5;
            game.length = 5;
            temp = game.gameWidth / game.snakePiece - 1;
            temp2 = game.gameHeight / game.snakePiece - 1;
            game.placeBorder(0, 0, temp, 0);
            game.placeBorder(temp, 0, temp, temp2);
            game.placeBorder(0, temp2, temp, temp2);
            game.placeBorder(0, 0, 0, temp2);
        }
        public void clearLvl()
        {
            int level = game.gameLvl + 1;
            gameTimer.Enabled = false;
            if (level > game.endLvl)
            {
                level = 1;
                game.gameDone();
                pictureBox.Refresh();
                game.status = 3;
                panel2.Visible = true;
            }
            else
            {
                game.lvlClear();
                panel3.Visible = true;
                pictureBox.Refresh();
                game = new Snake(user_ID);
                game.status = 5;
                game.gameLvl = level;
            }
        }

        void redo()
        {
            game = new Snake(user_ID);
            FormLoad(this, System.EventArgs.Empty);
            panel1.Visible = true;
        }

        private void button3_Click(object sender, EventArgs e)
        {
            panel2.Visible = false;
            redo();
            points.Text = "";
            level.Text = "";
            pictureBox.Refresh();
        }

        private void button5_Click(object sender, EventArgs e)
        {
            panel3.Visible = false;
            lvlMap();
            setup();
            game.status = 2;
            gameTimer.Enabled = true;
            this.Focus();
        }

        private void button4_Click(object sender, EventArgs e)
        {
            Application.Exit();
        }
    }
}