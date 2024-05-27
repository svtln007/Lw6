package Classes;

import java.util.*;

public class TV {
    private final Map<Integer, String> channelNumbersAndNames = new HashMap<>();
    private boolean isOn = false;
    private int selectedChannel = 1;
    private int previousChannel = 1;
    private final List<IDevice> devices = new ArrayList<>();

    public boolean isTurnedOn() {
        return isOn;
    }

    public void turnOn() {
        isOn = true;
    }

    public void turnOff() {
        isOn = false;
    }

    public int getChannel() {
        return isOn ? selectedChannel : 0;
    }

    public void connectDevice(IDevice device){
        devices.add(device);
    }

    public String printConnectedDevices(){
        StringJoiner result = new StringJoiner(",");
        for(var device : devices){
            result.add(device.getName());
        }
        return result.toString();
    }

    public boolean selectChannel(int channel) {
        previousChannel = selectedChannel;

        boolean isAvailableChannel = (channel >= 1) && (channel <= 99);
        if (isAvailableChannel && isOn) {
            selectedChannel = channel;
            return true;
        }
        return false;
    }

    boolean selectPreviousChannel() {
        if (isOn) {
            int temp = selectedChannel;
            selectedChannel = previousChannel;
            previousChannel = temp;
            return true;
        }
        return false;
    }

    public boolean deleteChannelName(String channelName) {
        if (isOn) {
            Iterator<Map.Entry<Integer, String>> iter = channelNumbersAndNames.entrySet().iterator();
            while (iter.hasNext()) {
                Map.Entry<Integer, String> entry = iter.next();
                if (channelName.equalsIgnoreCase(entry.getValue())) {
                    iter.remove();
                    return true;
                }
            }
        }
        return false;
    }

    public boolean setChannelName(int channel, String channelName) {
        if ((channel < 1 || channel > 99) || (channelName.equals(""))) {
            return false;
        }

        if (isOn) {
            deleteChannelName(channelName);
            channelNumbersAndNames.put(channel, channelName);
            return true;
        }
        return false;
    }

    public String getChannelName(int channel) {
        if (isOn) {
            for (Map.Entry<Integer, String> entry : channelNumbersAndNames.entrySet()) {
                if (entry.getKey() == channel) {
                    return entry.getValue();
                }
            }
        }
        return "";
    }

    public int getChannelByName(String channelName) {
        if (isOn) {
            for (Map.Entry<Integer, String> entry : channelNumbersAndNames.entrySet()) {
                if (entry.getValue().equals(channelName)) {
                    return entry.getKey();
                }
            }
        }
        return 0;
    }

    public boolean selectChannel(String channelName) {
        previousChannel = selectedChannel;
        int channel = getChannelByName(channelName);
        if (channel > 0) {
            selectedChannel = channel;
            return true;
        }
        return false;
    }
}