package net.thumbtack.school.library.Server;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import net.thumbtack.school.library.exceptions.ErrorCode;
import net.thumbtack.school.library.service.ServiceException;

public class JsonGetter {

        public static <T> T getClassFromJson( String jsonString, Class<T> classT) throws ServiceException {
            Gson gson = new Gson();
            try {
                return gson.fromJson(jsonString, classT);
            } catch (JsonSyntaxException e) {
                throw new ServiceException(ErrorCode.WRONG_JSON);
            }
        }
    }

