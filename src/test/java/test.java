import org.apache.commons.lang3.StringUtils;

import java.io.File;

public class test {
    public static void main(String[] args) {
        String s="/home/asdf/Workdir/IdeaProjects/IgIbGo/web/public/dapesh/videoCover/";
        System.out.println(StringUtils.substringAfterLast(s,"/public"));
    }
}
