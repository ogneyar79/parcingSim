import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class WorkJsoup {

    public static ArrayList getReplaced(String reg, Collection<String> string) {
        return string.stream().map(st -> st.split(reg)).map(spam -> Arrays.asList(spam))
                .flatMap(Collection::stream).collect(Collectors.toCollection(ArrayList::new));
    }

    public static ArrayList splitedByCamelCase(Collection<String> string) {
        return string.stream().map(st -> StringUtils.splitByCharacterTypeCamelCase(st)).map(spam -> Arrays.asList(spam))
                .flatMap(Collection::stream).collect(Collectors.toCollection(ArrayList::new));

    }

    public static void main(String... args) throws IOException {

        String ht = "https://www.simbirsoft.com";
        String html = "<html><body><p>текст1</p><p>текст2</p><img src=\"some.jpg\"><br><p>текст3<img src=\"another.jpg\"><br></body></html>";
        Pattern p = Pattern.compile(">([^<]*)<");
        Matcher m = p.matcher(getPage(ht).toString());
        ArrayList<String> matches = new ArrayList<>();
        while (m.find()) {
            String text = m.group(1);
            if (!text.isEmpty())
                matches.add(text);
        }

        ArrayList list = matches.stream().map(s -> s.replaceAll("[0-9]", "")).map(st -> st.split(" "))
                .map(spam -> Arrays.asList(spam)).flatMap(Collection::stream).map(y -> y.split("[\\W]  ")).map(spam -> Arrays.asList(spam))
                .flatMap(Collection::stream).collect(Collectors.toCollection(ArrayList::new));


        System.out.println(" b count 1;" + list.size());

        //     list.forEach(System.out::print);

        ArrayList l = getReplaced("[^a-z,A-Z,а-я,А-Я]", list);
        System.out.println(" b count 2 :" + list.size());
        l.forEach(System.out::println);

        //   getReplaced(Arrays.asList(regArray), s).forEach(System.out::println);
        //     String src = "c:/tmp/java_txt.par?t  1+rob-kds)Ф4Ж";
        // Arrays.stream(src.split("_|/|\\s | \\ |\\. ")).forEach(System.out::println);
        //      Arrays.stream(src.split("[^a-z,A-Z,а-я,А-Я]")).forEach(System.out::print);
        System.out.println("Comma!");

        ArrayList<String> lad = new ArrayList();
        lad.add("456,");
        lad.add("rt6,");
        lad.add(",567");

        System.out.println("L new");

        splitedByCamelCase(getReplaced(",", l)).forEach(System.out::println);
      

        boolean r = StringUtils.isAllUpperCase("redgf");
        boolean b = StringUtils.isMixedCase("EdR");
        System.out.println(" Bolean b :" + b);


    }

    public static Document getPage(String url) throws IOException {
        Document page = Jsoup.parse(new URL(url), 3000);
        return page;
    }
}




