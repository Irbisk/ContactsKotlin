package contacts

import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.adapters.PolymorphicJsonAdapterFactory
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.io.File

class BaseSerializer {
    companion object {
        private val file = File("phonebook.db")
        private val adapterFactory = PolymorphicJsonAdapterFactory
            .of(Base::class.java, "base")
            .withSubtype(Contact::class.java, "contacts")
            .withSubtype(Organization::class.java, "organization")
        private val moshi = Moshi.Builder()
            .add(adapterFactory)
            .addLast(KotlinJsonAdapterFactory())
            .build()
        private val type = Types.newParameterizedType(MutableList::class.java, Base::class.java,)
        private val baseAdapter = moshi.adapter<MutableList<Base>>(type)

        fun serialize() {
            val json = baseAdapter.toJson(contactsList)
            file.writeText(json)
        }

        fun deserialize() {
            if (file.exists()) {
                val text = file.readText().trimIndent()
                if (text.isNotEmpty()) {
                    contactsList = baseAdapter.fromJson(text)!!
                }
            }
        }
        fun deleteFile() {
            if (file.exists()) {
                file.delete()
            }
        }
    }
}