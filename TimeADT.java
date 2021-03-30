// Time and Event ADTs that allow processing of data using symbol table

import edu.princeton.cs.algs4.BinarySearchST;
import edu.princeton.cs.algs4.StdOut;

public class TimeADT {
    private class Time implements Comparable<Time> {
        private int hour;
        private int minute;
        private int second;

        public Time(int hour, int minute, int second) {
            if (hour < 0 || hour > 23) {
                throw new IllegalArgumentException();
            }
            if (minute < 0 || minute > 59) {
                throw new IllegalArgumentException();
            }
            if (second < 0 || second > 59) {
                throw new IllegalArgumentException();
            }

            this.hour = hour;
            this.minute = minute;
            this.second = second;
        }

        public int compareTo(Time that) {
            if (this.hour < that.hour) {
                return -1;
            }
            else if (this.hour > that.hour) {
                return 1;
            }
            else if (this.minute < that.minute) {
                return -1;
            }
            else if (this.minute > that.minute) {
                return 1;
            }
            else if (this.second < that.second) {
                return -1;
            }
            else if (this.second > that.second) {
                return 1;
            }
            return 0;
        }

        public String toString() {
            String hourString = String.valueOf(this.hour);
            String minuteString = String.valueOf(this.minute);
            String secondString = String.valueOf(this.second);
            if (this.hour < 10) {
                hourString = "0" + hourString;
            }
            if (this.minute < 10) {
                minuteString = "0" + minuteString;
            }
            if (this.second < 10) {
                secondString = "0" + secondString;
            }
            return hourString + ":" + minuteString + ":" + secondString;

        }
    }

    public class Event {
        private String location;

        public Event(String location) {
            this.location = location;
        }

        public String toString() {
            return location;
        }
    }

    public void compute() {
        BinarySearchST<Time, Event> schedule = new BinarySearchST<>();
        schedule.put(new Time(9, 0, 0), new Event("Chicago"));
        schedule.put(new Time(10, 50, 14), new Event("Phoenix"));
        schedule.put(new Time(9, 37, 44), new Event("Houston"));

        for (Time time : schedule.keys()) {
            StdOut.println(time.toString() + " (" + schedule.get(time) + ")");
        }
    }

    public static void main(String[] args) {
        new TimeADT().compute();
    }
}
