using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Text.RegularExpressions;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Animation;
using System.Windows.Shapes;

namespace Launcher
{
    public partial class MainPage : UserControl
    {
        List<ServiceReference1.User> userList = new List<ServiceReference1.User>();
        List<ServiceReference1.SnakeGame> snakeScoreList = new List<ServiceReference1.SnakeGame>();
        List<ServiceReference1.SnakeGame> snakeRecordList = new List<ServiceReference1.SnakeGame>();
        List<ServiceReference1.ShooterGame> shooterScoreList = new List<ServiceReference1.ShooterGame>();
        List<ServiceReference1.ShooterGame> shooterRecordList = new List<ServiceReference1.ShooterGame>();
        List<recordClass> recordList2 = new List<recordClass>();
        List<recordClass2> recordList3 = new List<recordClass2>();
        int userID;
        public MainPage()
        {
            InitializeComponent();
            ServiceReference1.Service1Client client = new ServiceReference1.Service1Client();
            client.getUsernamesAsync();
            client.getUsernamesCompleted += client_getUsernamesCompleted;
        }

        void client_getUsernamesCompleted(object sender, ServiceReference1.getUsernamesCompletedEventArgs e)
        {
            userList = e.Result.ToList();
        }

        private bool isEmail(string inputEmail)
        {
            Regex re = new Regex(@"^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,4}$",
                          RegexOptions.IgnoreCase);
            return re.IsMatch(inputEmail);
        }

        private void Button_Click(object sender, RoutedEventArgs e)
        {
            int check = 0;
            if (isEmail(username.Text) == false)
            {
                MessageBox.Show("Email is not correct!");
            }
            else if (userpass.Password.Trim().Length == 0)
            {
                MessageBox.Show("Write a password!");
            }
            else
            {
                for (int i = 0; i < userList.Count; i++)
                {
                    if (username.Text == userList[i].username)
                    {
                        if (userpass.Password == userList[i].password)
                        {
                            userID = userList[i].ID_user;
                            loginpanel.Visibility = Visibility.Collapsed;
                            tabPanel.Visibility = Visibility.Visible;
                            check = 1;
                            ServiceReference1.Service1Client client = new ServiceReference1.Service1Client();
                            client.getSnakeScoreDataAsync(userID);
                            client.getSnakeScoreDataCompleted += client_getSnakeScoreDataCompleted;
                        }
                    }
                }
                if (check == 0)
                {
                    MessageBox.Show("Incorrect password or username!");
                }
            }
        }

        void client_getSnakeScoreDataCompleted(object sender, ServiceReference1.getSnakeScoreDataCompletedEventArgs e)
        {
            snakeScoreList = e.Result.ToList();
            scoreData.ItemsSource = snakeScoreList;
            ServiceReference1.Service1Client client = new ServiceReference1.Service1Client();
            client.getSnakeRecordDataAsync();
            client.getSnakeRecordDataCompleted += client_getSnakeRecordDataCompleted;
        }

        void client_getSnakeRecordDataCompleted(object sender, ServiceReference1.getSnakeRecordDataCompletedEventArgs e)
        {
            snakeRecordList = e.Result.ToList();
            ServiceReference1.Service1Client client = new ServiceReference1.Service1Client();
            client.getShooterScoreDataAsync(userID);
            client.getShooterScoreDataCompleted += client_getShooterScoreDataCompleted;
        }

        void client_getShooterScoreDataCompleted(object sender, ServiceReference1.getShooterScoreDataCompletedEventArgs e)
        {
            shooterScoreList = e.Result.ToList();
            scoreData2.ItemsSource = shooterScoreList;
            ServiceReference1.Service1Client client = new ServiceReference1.Service1Client();
            client.getShooterRecordDataAsync();
            client.getShooterRecordDataCompleted += client_getShooterRecordDataCompleted;
        }

        void client_getShooterRecordDataCompleted(object sender, ServiceReference1.getShooterRecordDataCompletedEventArgs e)
        {
            shooterRecordList = e.Result.ToList();
        }

        private void Button_Click_1(object sender, RoutedEventArgs e)
        {
            loginpanel.Visibility = Visibility.Collapsed;
            register.Visibility = Visibility.Visible;

        }

        private void Button_Click_2(object sender, RoutedEventArgs e)
        {
            int check = 0;
            if (isEmail(registerName.Text) == false)
            {
                MessageBox.Show("Email is not correct!");
            }
            else if (registerPass.Password.Trim().Length == 0)
            {
                MessageBox.Show("Write a password!");
            }
            else
            {
                for (int i = 0; i < userList.Count; i++)
                {
                    if (registerName.Text == userList[i].username)
                    {
                        MessageBox.Show("Username already taken!");
                        check = 1;
                    }
                }
                if (check == 0)
                {
                    ServiceReference1.User u = new ServiceReference1.User();
                    u.username = registerName.Text;
                    u.password = registerPass.Password;
                    ServiceReference1.Service1Client client = new ServiceReference1.Service1Client();
                    client.addUserAsync(u);
                    client.addUserCompleted += client_addUserCompleted;                 
                }
            }
        }

        void client_addUserCompleted(object sender, System.ComponentModel.AsyncCompletedEventArgs e)
        {
            ServiceReference1.Service1Client client = new ServiceReference1.Service1Client();
            client.getUsernamesAsync();
            client.getUsernamesCompleted += client_getUsernamesCompleted2;
        }

        private void client_getUsernamesCompleted2(object sender, ServiceReference1.getUsernamesCompletedEventArgs e)
        {
            userList = e.Result.ToList();
            MessageBox.Show("Your registration has been completed successfully!");
        }

        private void Button_Click_3(object sender, RoutedEventArgs e)
        {
            register.Visibility = Visibility.Collapsed;
            loginpanel.Visibility = Visibility.Visible;
        }

        private void ComboBox_SelectionChanged(object sender, SelectionChangedEventArgs e)
        {
            recordData.ItemsSource = null;
            recordData2.ItemsSource = null;
            recordList2.Clear();
            recordList3.Clear();
            int skaits = 0;
            if (amount.SelectedIndex == 0)
            {
                skaits = 5;
            }
            else if (amount.SelectedIndex == 1)
            {
                skaits = 10;
            }
            else if (amount.SelectedIndex == 2)
            {
                skaits = 20;
            }
            if (pickGameRecord.SelectedIndex == 0)
            {
                snakeRecordPanel.Visibility = Visibility.Visible;
                shooterRecordPanel.Visibility = Visibility.Collapsed;
                if (skaits < snakeRecordList.Count)
                {
                    for (int i = 0; i < skaits; i++)
                    {
                        for (int j = 0; j < userList.Count; j++)
                        {
                            if (snakeRecordList[i].ID_user == userList[j].ID_user)
                            {
                                recordClass temp = new recordClass();
                                temp.username = userList[j].username;
                                temp.score = (int)snakeRecordList[i].score;
                                temp.level = (int)snakeRecordList[i].lvl;
                                recordList2.Add(temp);
                                break;
                            }
                        }
                    }
                }
                else
                {
                    for (int i = 0; i < snakeRecordList.Count; i++)
                    {
                        for (int j = 0; j < userList.Count; j++)
                        {
                            if (snakeRecordList[i].ID_user == userList[j].ID_user)
                            {
                                recordClass temp = new recordClass();
                                temp.username = userList[j].username;
                                temp.score = (int)snakeRecordList[i].score;
                                temp.level = (int)snakeRecordList[i].lvl;
                                recordList2.Add(temp);
                                break;
                            }
                        }
                    }
                }
                recordData.ItemsSource = recordList2;
            }
            else if (pickGameRecord.SelectedIndex == 1)
            {
                snakeRecordPanel.Visibility = Visibility.Collapsed;
                shooterRecordPanel.Visibility = Visibility.Visible;
                if (skaits < shooterRecordList.Count)
                {
                    for (int i = 0; i < skaits; i++)
                    {
                        for (int j = 0; j < userList.Count; j++)
                        {
                            if (shooterRecordList[i].ID_user == userList[j].ID_user)
                            {
                                recordClass2 temp = new recordClass2();
                                temp.username = userList[j].username;
                                temp.score = (int)shooterRecordList[i].score;
                                recordList3.Add(temp);
                                break;
                            }
                        }
                    }
                }
                else
                {
                    for (int i = 0; i < shooterRecordList.Count; i++)
                    {
                        for (int j = 0; j < userList.Count; j++)
                        {
                            if (shooterRecordList[i].ID_user == userList[j].ID_user)
                            {
                                recordClass2 temp = new recordClass2();
                                temp.username = userList[j].username;
                                temp.score = (int)shooterRecordList[i].score;
                                recordList3.Add(temp);
                                break;
                            }
                        }
                    }
                }
                recordData2.ItemsSource = recordList3;
            }
        }

        private void pickGameScore_SelectionChanged(object sender, SelectionChangedEventArgs e)
        {
            if (pickGameScore.SelectedIndex == 0)
            {
                shooterScorePanel.Visibility = Visibility.Collapsed;
                snakeScorePanel.Visibility = Visibility.Visible;
            }
            else if (pickGameScore.SelectedIndex == 1)
            {
                snakeScorePanel.Visibility = Visibility.Collapsed;
                shooterScorePanel.Visibility = Visibility.Visible;
            }
        }

    }
    public class recordClass
    {
        public string username { get; set; }
        public int score { get; set; }
        public int level { get; set; }
    }

    public class recordClass2
    {
        public string username { get; set; }
        public int score { get; set; }
    }
}
