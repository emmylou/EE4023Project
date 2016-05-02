/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSDestinationDefinition;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/*
Emmylou Flores 12132403
Ankit Kumar Baliyan 13009753
Joshua Hetherington 13061283
Vishrut Krashak 12085766
*/

/**
 *
 * @author ankit
 */
@JMSDestinationDefinition(name = "java:app/MyMsgQueue", interfaceName = "javax.jms.Queue", resourceAdapter = "jmsra", destinationName = "MyMsgQueue")
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "java:app/MyMsgQueue"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class NewMessageBean implements MessageListener {
    
    public NewMessageBean() {
    }
    
    @Override
    public void onMessage(Message message) {
        try {
            TextMessage gmsg = (TextMessage) message;
            System.out.println(gmsg.getText());
        } catch (JMSException ex) {
            Logger.getLogger(NewMessageBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
