# UTM for Liferay Forms

https://en.wikipedia.org/wiki/UTM_parameters

This module adds the UTM parameters to the Liferay session and adds them
as workflow context parameters when a Liferay form is submitted so that
you can use them like this is in a workflow groovy script:

```
System.out.println("Entering workflow");
System.out.println("UTM source: " + workflowContext.get("utm_source"));
System.out.println("UTM campaign: " + workflowContext.get("utm_campaign"));
System.out.println("UTM medium: " + workflowContext.get("utm_medium"));
System.out.println("UTM term: " + workflowContext.get("utm_term"));
System.out.println("UTM content: " + workflowContext.get("utm_content"));
```

## Handling user login

If the user logs after landing on the site but before submitting the form,
you need to add this portal property:

```
session.phishing.protected.attributes=\
        HTTPS_INITIAL,\
        LAST_PATH,\
        SAML_SP_SESSION_KEY,\
        SAML_SSO_REQUEST_CONTEXT,\
        SETUP_WIZARD_PASSWORD_UPDATED,\
        utm_session_attribute
```

When a user logs in, his session is replaced with a new one (this is a
protection against session fixation phishing attempts).

This property white lists `utm_session_attribute` so that it is not lost
during the transition to the new session.

## Modern java

Requires 

```
--add-opens=java.base/sun.util.calendar=ALL-UNNAMED
```
