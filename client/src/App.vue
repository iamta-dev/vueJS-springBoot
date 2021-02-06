<template>
  <v-app>
  <v-app-bar
      app
      color="rgb(24,103,193)"
      dark
    >
      <span class="display-1 font-italic"> STUDENTS INFORMATION </span>
      <v-spacer></v-spacer>
      <v-btn
          
            style="margin-right: 20px"
            @click="redirectStudent"
          >
            <v-icon size="24px" style="margin-right: 5px">fas fa-user-plus</v-icon>
            <h2>STUDENT</h2>
      </v-btn>
      <v-btn
     
            style="margin: 0"
            @click="redirectTable"
          >
            <v-icon size="24px" style="margin-right: 5px">fas fa-table</v-icon>
            <h2>TABLE</h2>
      </v-btn>
       
    </v-app-bar>
    <v-main color="#f2f3f4">
      <v-col></v-col>
      <router-view/>
    </v-main>
  </v-app>
</template>

<script>
export default {
  name: 'App',
  data: () => ({
  
  }),
  methods: {
    /* eslint-disable no-console */
    redirectStudent() {
      this.$router.push("/");
    },
    redirectTable() {
      this.$router.push("/Table");
    }
  },  
};
</script>