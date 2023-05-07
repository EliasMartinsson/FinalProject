import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EventBooker extends JFrame {
    private JPanel panel;
    private JButton[] dayButtons;
    private Map<JButton, Event> buttonToEventMap;
    private ArrayList<Event> events;

    public EventBooker() {
        super("Library Event Booking System");

        // initialize the panel
        panel = new JPanel(new GridLayout(5, 7));

        // initialize the array of day buttons
        dayButtons = new JButton[10];
        buttonToEventMap = new HashMap<>();
        events = new ArrayList<>();

        // add day buttons to the panel
        for (int i = 0; i < 10; i++) {
            JButton button = new JButton(String.valueOf(i + 1));
            button.addActionListener(e -> handleButtonClick(button));
            dayButtons[i] = button;
            panel.add(button);
        }

        // add the panel to the frame
        add(panel);

        // set the frame properties
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1920, 1080);
        setVisible(true);
    }

    private void handleButtonClick(JButton button) {
        Event event = buttonToEventMap.get(button);
        if (event == null) {
            // the day is available let the user add event
            String eventName = JOptionPane.showInputDialog(this, "Enter the event name:");
            if (eventName != null && !eventName.isEmpty()) {
                event = new Event(eventName);
                buttonToEventMap.put(button, event);
                events.add(event);
                button.setBackground(Color.GREEN);
            }
        } else {
            // let the user view or delete events
            String[] options = {"View Event", "Delete Event"};
            int choice = JOptionPane.showOptionDialog(this, "This day has an event. What do you want to do?", "Event Options", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            if (choice == 0) {
                JOptionPane.showMessageDialog(this, "Event: " + event.getName());
            } else if (choice == 1) {
                buttonToEventMap.remove(button);
                events.remove(event);
                button.setBackground(null);
            }
        }
    }
}