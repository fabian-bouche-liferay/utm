# UTM for Liferay Forms

https://en.wikipedia.org/wiki/UTM_parameters

This module adds the UTM parameters to the Liferay session and adds them
as workflow context parameters when a Liferay form is submitted so that
you can use them like this is a workflow groovy script:

```
System.out.println("Entering workflow");
System.out.println("UTM source: " + workflowContext.get("utm_source"));
System.out.println("UTM campaign: " + workflowContext.get("utm_campaign"));
System.out.println("UTM medium: " + workflowContext.get("utm_medium"));
System.out.println("UTM term: " + workflowContext.get("utm_term"));
System.out.println("UTM content: " + workflowContext.get("utm_content"));
```
