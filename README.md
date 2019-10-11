
<table align="center"><tr><td align="center" width="9999">

# Java Application Socket TCP

File sharing service, Chat
</td></tr></table>


Java Application where users can share their files with others and make requests at the same time, communication is done using the TCP protocol.

The TCP (Transmission Control Protocol) protocol establishes a point-to-point communication conduit between two computers, that is, when the transmission of a data stream between two computers is required, the TCP protocol establishes an exclusive conduit between those computers by the which data will be transmitted and this will last until the transmission is finished, thanks to this TCP guarantees that the data sent from one end of the connection reaches the other end and in the same order in which they were sent.

There are two different parts Client - Server.

<strong>SERVER:</strong> The server will manage the communication between the different clients.
When a client connects, it will transmit the information of its available files and the server will manage its availability.
When a user makes a request /get <filename>, he will connect this user with the owner of the file. The server accepts requests from multiple users (BapsterServer Class) and manages them through threads (BapsterServerThread Class).
   
<strong>USERS (CLIENTS):</strong> They will begin communication with the server, they will communicate to the server the files that it makes available to share.,  the / w command can be sent emails, for this task the JavaMail library was used.  You can also execute orders.
   

## Autor
Grace Toa (App Developer)

## Feature
- Java [Documentation](https://docs.oracle.com/en/java/)
- JavaMail Api [Documentation](https://javaee.github.io/javamail/docs/api/)
- Eclipse EE IDE

## Link
- JavaAplication: [Youtube](https://youtu.be/IND32gHCRyc)

<table border="3" bordercolor="black" align="center" width="9999">
    <tr>
        <th> Eclipse IDE</th> 
    </tr>
      
<tr align="center">
     <td ><img src="https://user-images.githubusercontent.com/10947013/65537143-f7b1d200-df04-11e9-8f39-431a63c35e7c.PNG"             width="550" alt="email"></td>          
            </tr>
     <tr  align="center">
        <td><img src="https://user-images.githubusercontent.com/10947013/65537220-2039cc00-df05-11e9-8bce-80e6eb0a1c0b.PNG" 
         width="450"   alt="client1"  </td>
</tr>
     <tr  align="center">
        <td><img src="https://user-images.githubusercontent.com/10947013/65537310-47909900-df05-11e9-9f17-01ecf07e4a29.PNG" 
        width="450"   alt="client2"  </td>
</tr>
</table>
