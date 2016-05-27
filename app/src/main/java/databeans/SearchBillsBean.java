package databeans;

import java.util.List;

/**
 * Created by Constantine on 2016/5/27.
 */
public class SearchBillsBean {

    public String message;
    public int code;
    public DatumBean datum;

    public static class DatumBean {

        public boolean lastPage;
        public int pageSize;
        public int pageNumber;
        public boolean firstPage;
        public int totalRow;
        public int totalPage;
        public List<ListBean> list;

        public static class ListBean {

            public String hitch_des;
            public String process_Id;
            public String task_name;
            public String create_time;
            public boolean treat_timeout;
            public String order_id;
            public String bill_type;
            public String bill_id;
            public String sub_category;
            public boolean reply_timeout;
        }
    }
}
