using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Diagnostics;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;

namespace WpfApplication1
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        List<User> userList = new List<User>();
        int userID;
        public MainWindow()
        {
            InitializeComponent();
        }

        private bool isEmail(string inputEmail)
        {
            Regex re = new Regex(@"^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,4}$",
                          RegexOptions.IgnoreCase);
            return re.IsMatch(inputEmail);
        }

        private void Button_Click_1(object sender, RoutedEventArgs e)
        {
            string connection = string.Format("Data Source=PHO-PC;Initial Catalog=game;User ID={0};pwd={1};", "sa", "parole");
            SqlConnection con = new SqlConnection(connection);
            SqlCommand command = new SqlCommand("SELECT * FROM dbo.[User]", con);
            con.Open();
            SqlDataReader reader = command.ExecuteReader();
            while (reader.Read())
            {
                User u = new User();
                u.ID_user = (int)reader["ID_user"];
                u.username = reader["username"].ToString();
                u.password = reader["password"].ToString();
                userList.Add(u);
            }

            reader.Close();
            con.Close();
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
                            login.Visibility = Visibility.Hidden;
                            gamePanel.Visibility = Visibility.Visible;
                            gamePanel2.Visibility = Visibility.Visible;
                            check = 1;
                            userID = userList[i].ID_user;
                        }
                    }
                }
                if (check == 0)
                {
                    MessageBox.Show("Incorrect password or username!");
                }
            }
        }

        private void Button_Click(object sender, RoutedEventArgs e)
        {

            Snake.Form1 game = new Snake.Form1();
            game.user_ID = userID;
            game.Show();
        }

        private void Button_Click_2(object sender, RoutedEventArgs e)
        {
            Process.Start(@"D:\Folder\Visual Studio 2013\Snake\Shooter\Shooter\bin\x86\Debug\Shooter.exe", userID+"");
        }
    }
}
