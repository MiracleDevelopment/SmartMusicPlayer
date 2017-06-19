# Tutorial SmartMusicPlayer alpha 0.1.2 Beta #
![a4104e9fb893882633276a1612987983.jpg](https://www.img.in.th/images/a4104e9fb893882633276a1612987983.jpg)
#### This Library smartMusicPlayer is library has been extend from musicplayer. I have myidea to custom reuse function as easy for use and public give other developer android try to use and i hope you enjoy with myLibrary ####
- - - -
## User Guide ##
### First SetUp ###
### you should add dependency in build.gradle level app ###
`compile 'com.miraclelab:SmartMusicPlayer:0.1.2'`
### OR maven ###
 ````
 <dependency>
  <groupId>com.miraclelab</groupId>
  <artifactId>SmartMusicPlayer</artifactId>
  <version>0.1.2</version>
  <type>pom</type>
</dependency> 
 ````

#### Now the last update java coding and nothing xml file !!! ####
#### Start Used ####
#### You can call class SmartMediaPlayer is local variable ####
 ````
  SmartMediaPlayer smartMediaPlayer;
 ````
#### When you use. You need to new Object and pass parameter ####
 ````
  smartMediaPlayer = new SmartMediaPlayer(getApplicationContext(), mMediaPlayer, R.raw.labanoon);
 ````
Parameter     | Type
--------------| ---------
mContext      |  Context
mMediaPlayer  |  MediaPlayer
File / Integer|  SourceMusic

#### Function Start , Pause , Next , Prev ####
#### Start You can call method OnMusicStartListener(); ####
 ````
  smartMediaPlayer.OnMusicStartListener();
 ````
 ##### **** smartMediaPlayer is local variable of me **** #####
 ##### Pause #####
  ````
  smartMediaPlayer.OnMusicPauseListener();
 ````
 #### Next ####
  ````
   smartMediaPlayer.OnMusicNextListener();
 ````
 #### Prev ####
 ````
   smartMediaPlayer.OnMusicPrevListener();
 ````
 #### **** When you call Next and Prev you have to parameter in musicplayer as well as list of filemusic **** ####
 #### But i think to different about function. I have  image Cover give other use when he playing music image can show Cover of music current ####
 ### ImageCover ###
 #### You can call method setBitmapImageCover ####
 ````
     smartMediaPlayer.setBitmapImageCover(getApplicationContext()
                        , R.mipmap.ic_launcher, imAlbum
                        , true);
 ````
 #### And you need to pass Parameter is Context, PlaceHolder , imageView, cropCirclePhoto is boolean ####
 
Parameter         |  Type
---------------   | ------------------
mContext          | Context
Integer / Drawable| PlaceHolder
ImageView         | imageView 
cropCirclePhoto   | boolean

### Final  i hope you enjoy when you use it and if you have to issue in library you can send meassage to coming in issue now. ###
 
 

 


