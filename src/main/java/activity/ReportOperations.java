package activity;

public final class ReportOperations {
    public static Reporter findChild(final Reporter parentReporter, final String name) {
       if(parentReporter.getChildReporters().containsKey(name)) {
           return parentReporter.getChild(name);
       }

       Reporter reporter = null;

       for(Reporter childReporter : parentReporter.getAllChildren()) {
           reporter = findChild(childReporter, name);

           if(reporter != null) {
               return reporter;
           }
       }

       return reporter;
    }
}
