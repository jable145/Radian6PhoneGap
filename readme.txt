This is a Radian6 Demo for deployment on Heroku.

It utilizes embedded Tomcat.

Here's the Heroku url

http://warm-warrior-5695.herokuapp.com/


Note that this supports Servlets and JSP.

To run this locally try this: (from command line)

mvn clean install
sh target/bin/webapp
then open a browser at http://localhost:8080

OR

foreman start
then open a browser at http://localhost:5000/logon.html

To run on Heroku try this:

git init
echo target > .gitignore
git add .
git commit -m "latest"

git push heroku master

This should push to Heroku and redeploy.
Check that it is running ok via

heroku logs
heroku ps
heroku open

if the logs show an error H99 then that indicates a Heroku Platform error and you need to raise an incident with Heroku.