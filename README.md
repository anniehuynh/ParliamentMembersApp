# ParliamentMembersApp
##About the Project

The target of the project is to design and implement an application that allows user to browse information about members of the
parliament. It should also be possible to add comments about the MP and give them grades about their performance.

##What are in the app?
### Retrofit and moshi libraries are used for reading the data. 
### Data is stored a Room database in the Android device. 
### Define class(es) to store grades and comments and create Room database tables from them.
### Pictures of MPs are available from URL starting with https://avoindata.eduskunta.fi/ and ending with MP-specific part that is part of
the MP basic data. 
### Implement repository class(es) for database / image store access.
### Implement app views as fragments and use navigation mechanism to move from a view to another. Implement a view model for each
view and use binding mechanism. 
### RecyclerView is used for displaying collections of information.
### Live data to update views from database / image storage.
