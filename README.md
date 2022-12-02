KPlayer                         
                                                                                                       
It's a plugin based on creating realistic fake players.
This plugin allows you to do several things. Here are the available options

This plugin was made for a server, so it is unfortunately impossible to download it for the moment.


==============================================================================================================


This command will answer an informative message that can be completely changed from the configuration.
The most relevant information it provides is the plugin version and a very useful subcommand: /kp help.
Usage: /kp
Permissions: kplayer.info

Sub Commands
help
This command will reply a message with all the available commands of the plugin. This message is fully customizable from the plugin configuration.
Usage: /kp help
Permissions: kplayer.help

connect
Use this command to connect fake players to your server. You can use any name you want and add as many as you want.
Usage: /kp connect [name] (amount)
Permissions: kplayer.connect

disconnect
Use this command to disconnect fake players to your server.
Usage: /kp disconnect [name/all]
Permissions: kplayer.disconnect

teleport
Teleport the fake players to the location of your choice. Supports coordinates and players.
Usage: /kp teleport [name] [X, Y, Z or player]
Permissions: kplayer.teleport

chat
Chat through a fake player's account. This can be very useful when a real player is talking to a fake player and there is no answer configured.
Usage: /kp chat [name/all] [message]
Permissions: kplayer.chat

sudo
Execute a command through a fake player. You can use it to send private messages to real players or any other function.
Usage: /kp sudo [player] [command]
Permissions: kplayer.sudo

list
Access the list of all fake players connected to the server. Sometimes it is difficult to distinguish real players from fake players.
Usage: /kp list
Permissions: kplayer.list

reload
Reload the server configuration. Note that some options need to restart the entire server to be applied.
Usage: /kp reload
Permissions: kplayer.reload
