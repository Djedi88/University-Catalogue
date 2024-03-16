package Catalog;

import Users.Assistant;
import Users.Teacher;

public interface Visitor {
        void visit(Assistant assistant);
        void visit(Teacher teacher);


}
