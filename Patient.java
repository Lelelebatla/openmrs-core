public class Patient {

    private String person_name_id, person_id, prefix, given_name, middle_name, family_name, family_name_suffix, creator, date_created, voided, void_reason, uuid;

    public Patient (String person_name_id, String person_id, String prefix, String given_name, String middle_name, String family_name, String family_name_suffix, String creator, String date_created, String voided, String void_reason, String uuid) {
        
        this.person_name_id     = person_name_id;
        this.person_id          = person_id;
        this.prefix             = prefix;
        this.given_name         = given_name;
        this.middle_name        = middle_name;
        this.family_name        = family_name;
        this.family_name_suffix = family_name_suffix;
        this.creator            = creator;
        this.date_created       = date_created;
        this.voided             = voided;
        this.void_reason        = void_reason;
        this.uuid               = uuid;


    }

    public String get_person_name_id() {
        return this.person_name_id ;
    }
    
    public String get_given_name() {
        return this.given_name ;
    }
    
    public String get_family_name () {
        return this.family_name  ;
    }
    
 
    
}