package ehu.isad;

import java.util.Arrays;

public class Details {
        String[] publishers;
        Integer number_of_pages;
        String title;

        public Integer getNumber_of_pages() {
                return number_of_pages;
        }

        public String getTitle() {
                return title;
        }

        public String[] getPublishers() {
                return publishers;
        }

        @Override
        public String toString() {
            return "Details{" +
                    "publishers=" + Arrays.toString(publishers) +
                    ", number_of_pages=" + number_of_pages +
                    ", title='" + title + '\'' +
                    '}';
        }
}
