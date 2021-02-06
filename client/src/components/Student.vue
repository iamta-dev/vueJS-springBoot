<template>
  <div>
    <v-row justify="center">
      <v-card style="background-color: #ffffff; margin: 40px" width="800">
        <v-app-bar dark color="#1A76D2">
          <v-toolbar-title><h3>ADD STUDENT</h3></v-toolbar-title>

          <v-spacer></v-spacer>
        </v-app-bar>

        <v-card-text>
          <v-container
            style="color: black; margin-top: 50px; padding-bottom: 30px"
          >
            <v-row>
              <v-col cols="8" style="margin: auto">
                <v-text-field
                  v-model="Student.stuCode"
                  label="Fill Student Code"
                  outlined
                ></v-text-field>
              </v-col>
            </v-row>

            <v-row>
              <v-col cols="8" style="margin: auto">
                <v-text-field
                  v-model="Student.name"
                  label="Fill Name"
                  outlined
                ></v-text-field>
              </v-col>
            </v-row>

            <v-row>
              <v-col cols="8" style="margin: auto">
                <v-text-field
                  v-model="Student.subject"
                  label="Fill Subject"
                  outlined
                ></v-text-field>
              </v-col>
            </v-row>

            <v-row>
              <v-col cols="8" style="margin: auto">
                <v-text-field
                  v-model="Student.tel"
                  label="Fill Phone Number"
                  outlined
                ></v-text-field>
              </v-col>
            </v-row>

            <v-row>
              <v-spacer></v-spacer>

              <v-btn
                class="font-weight-light"
                color="primary"
                width="120"
                height="40"
                @click="chackList"
                ><h3>SAVE DATA</h3></v-btn
              >
            </v-row>
          </v-container>
        </v-card-text>
      </v-card>
    </v-row>

    <!-- popup Success -->
    <template>
      <v-row justify="center">
        <v-dialog v-model="popup.Success" max-width="500px">
          <v-card style="background-color: #f2f3f4">
            <v-card-title>
              <span class="display-1 font-weight-light"
                >Successful process</span
              >

              <v-spacer></v-spacer>

              <v-btn icon>
                <v-icon size="24px" @click="popup.Success = false"
                  >fas fa-times</v-icon
                >
              </v-btn>
            </v-card-title>

            <v-card-text>
              <v-container>
                <v-row>
                  <Label >{{ popup.TextSuccess }}</Label>
                </v-row>
                <v-row>
                  <v-spacer></v-spacer>
                  <v-btn
                    @click="popup.Success = false"
                    class="font-weight-light"
                    color="primary"
                    width="100"
                    height="20"
                    >close</v-btn
                  >
                </v-row>
              </v-container>
            </v-card-text>
          </v-card>
        </v-dialog>
      </v-row>
    </template>

    <!-- popup Error -->

    <template>
      <v-row justify="center">
        <v-dialog v-model="popup.Error" max-width="500px">
          <v-card style="background-color: #f2f3f4">
            <v-card-title>
              <span class="display-1 font-weight-light">Error process</span>

              <v-spacer></v-spacer>

              <v-btn icon>
                <v-icon size="24px" @click="popup.Error = false"
                  >fas fa-times</v-icon
                >
              </v-btn>
            </v-card-title>

            <v-card-text>
              <v-container>
                <v-row>
                  <Label >{{ popup.TextError }}</Label>
                </v-row>
                <v-row>
                  <v-spacer></v-spacer>
                  <v-btn
                    @click="popup.Error = false"
                    class="font-weight-light"
                    color="error"
                    width="100"
                    height="20"
                    >close</v-btn
                  >
                </v-row>
              </v-container>
            </v-card-text>
          </v-card>
        </v-dialog>
      </v-row>
    </template>
  </div>
</template>

<script>
import http from "../Api";

export default {
  name: "student",
  data() {
    return {
      Student: {
        stuCode: "",
        name: "",
        subject: "",
        tel: "",
      },
      popup: {
        TextSuccess: "TextSuccess",
        Success: false,
        TextError: "TextError",
        Error: false,
      },
    };
  },

  methods: {
    /* eslint-disable no-console */

    saveStudent() {
      http
        .post(
          "/saveStudent/" +
            this.Student.stuCode +
            "/" +
            this.Student.name +
            "/" +
            this.Student.subject +
            "/" +
            this.Student.tel +
            "/",
          this.student
        )
        .then((response) => {
          console.log(response);
          this.resetData();

          this.popup.Success = true;
          this.popup.TextSuccess = "OK !,Data has been saved successfully.";
        })
        .catch((e) => {
          console.log(e);
          this.resetData();
        });
    },
    chackList() {
      if (
        this.Student.stuCode != "" &&
        this.Student.name != "" &&
        this.Student.subject != "" &&
        this.Student.tel != ""

      ) {
        this.saveStudent();
      } else {

        this.popup.Error = true;
        this.popup.TextError = "Please Fill all information.";
      }
    },
    resetData() {
      this.Student.stuCode = "";
      this.Student.name = "";
      this.Student.subject = "";
      this.Student.tel = "";
    },
    /* eslint-disable no-console */
  },
};
</script>