<template>
  <v-dialog v-model="dialog" max-width="600px">
    <v-btn slot="activator" class="success" flat>Add New Project</v-btn>
    <v-card>
      <v-card-title>
        <h2>Add a New Project</h2>
      </v-card-title>
      <v-card-text>
        <v-form ref="form" class="px-3">
          <v-text-field v-model="title" :rules="inputRules" label="Title" prepend-icon="folder"></v-text-field>
          <v-textarea v-model="content" :rules="inputRules" label="Information" prepend-icon="edit"></v-textarea>

          <v-menu v-model="menu" :close-on-content-click="false">
            <v-text-field slot="activator" :rules="inputRules" :value="formattedDate" clearable label="Due date"
                          prepend-icon="date_range">
            </v-text-field>
            <v-date-picker v-model="due" @change="menu = false"></v-date-picker>
          </v-menu>

          <v-spacer></v-spacer>

          <v-btn :loading="loading" class="success mx-0 mt-3" flat @click="submit">Add Project</v-btn>
        </v-form>
      </v-card-text>
    </v-card>
  </v-dialog>
</template>

<script>
import format from 'date-fns/format'
import db from '@/fb'

export default {
  data() {
    return {
      title: '',
      content: '',
      due: null,
      menu: false,
      inputRules: [
        v => !!v || 'This field is required',
        v => v.length >= 3 || 'Minimum length is 3 characters'
      ],
      loading: false,
      dialog: false
    }
  },
  methods: {
    submit() {
      if (this.$refs.form.validate()) {
        this.loading = true
        const project = {
          title: this.title,
          content: this.content,
          due: format(this.due, 'Do MMM YYYY'),
          person: 'The Net Ninja',
          status: 'ongoing'
        }
        db.collection('projects').add(project).then(() => {
          this.loading = false
          this.dialog = false
          this.$emit('projectAdded')
        })
      }
    }
  },
  computed: {
    formattedDate() {
      console.log(this.due)
      return this.due ? format(this.due, 'Do MMM YYYY') : ''
    }
  }
}
</script>
