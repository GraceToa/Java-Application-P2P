
<table align="center"><tr><td align="center" width="9999">

# Java Application Socket TCP

File sharing service, Chat
</td></tr></table>


Java Application where users can share their files with others and make requests at the same time, communication is done using the TCP protocol.

The TCP (Transmission Control Protocol) protocol establishes a point-to-point communication conduit between two computers, that is, when the transmission of a data stream between two computers is required, the TCP protocol establishes an exclusive conduit between those computers by the which data will be transmitted and this will last until the transmission is finished, thanks to this TCP guarantees that the data sent from one end of the connection reaches the other end and in the same order in which they were sent.

There are two different parts Client - Server.

SERVER: The server will manage the communication between the different clients.
When a client connects, it will transmit the information of its available files and the server will manage its availability.
When a user makes a request /get <filename>, he will connect this user with the owner of the file. The server accepts requests from multiple users (BapsterServer Class) and manages them through threads (BapsterServerThread Class).
   
USERS (CLIENTS): They will begin communication with the server, they will communicate to the server the files that it makes available to share.,  the / w command can be sent emails, for this task the JavaMail library was used.  You can also execute orders.
   

## Autor
Grace Toa (App Developer)

## Feature
- Java [Documentation](https://docs.oracle.com/en/java/)
- JavaMail Api [Documentation](https://javaee.github.io/javamail/docs/api/)
- Eclipse EE

## Link
- JavaAplication: [Youtube]()

<table border="3" bordercolor="black" align="center">
    <tr>
        <th colspan="3">Baster </th> 
    </tr>
    <tr>
        <td><img src="https://user-images.githubusercontent.com/10947013/54477287-dc079980-4806-11e9-8a69-26dcc77d4327.png"             width="250" alt="login"></td>
        <td><img src="https://user-images.githubusercontent.com/10947013/54477347-25f07f80-4807-11e9-9afb-d5939475d59a.png"             width="250" alt="register"></td>
        <td><img src="https://user-images.githubusercontent.com/10947013/54477368-57694b00-4807-11e9-85f5-d7f1c98e8739.png"              width="250" alt="profile"></td>     
    </tr>
 
 
</table>
