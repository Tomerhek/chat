package server;

import static org.junit.Assert.*;
import org.junit.Test;

public class TestJunitChatServer {

    @Test
    public void test() {

        ChatServer nsg = new ChatServer(8989);
        int portNum = nsg.port;
        assertEquals(8989, portNum);

    }
}
