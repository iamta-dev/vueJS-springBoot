<template>
  <div>
    <div align="center" justify="center">
      <v-card width="1000" style="margin: 40px">
        <v-card-title>
          <label style="margin-bottom: 30px; margin-left: 20px"
            ><h3>Table List Students</h3></label
          >
          <v-spacer></v-spacer>
          <!-- search student_code -->
          <v-text-field
            v-model="field_search"
            label="Search by StudentCode"
            solo
            rounded
          ></v-text-field>
          <v-btn
            icon
            style="position: relative; right: 50px; bottom: 15px; margin: 0"
            @click="getStudentByCode"
          >
            <v-icon size="24px">fas fa-search</v-icon>
          </v-btn>
        </v-card-title>

        <v-simple-table>
          <template v-slot:default>
            <thead>
              <tr>
                <th class="text-left">ID</th>
                <th class="text-left">STUDENT CODE</th>
                <th class="text-left">NAME</th>
                <th class="text-left">SUBJECT</th>
                <th class="text-left">PHONE NUMBER</th>
                <th class="text-left">ACTION</th>
              </tr>
            </thead>

            <tbody v-if="view.data">
              <tr v-for="item in viewStudent" :key="item.id">
                <td>{{ item.id }}</td>
                <td>{{ item.stuCode }}</td>
                <td>{{ item.name }}</td>
                <td>{{ item.subject }}</td>
                <td>{{ item.tel }}</td>
                <td>
                  <v-btn
                    v-on:click="showDataStudent(item)"
                    icon
                    color="green"
                    style="position: relative; bottom: 2px; margin-right: 5px"
                  >
                    <v-icon id="editIcon" size="18px">fas fa-edit</v-icon>
                  </v-btn>

                  <v-btn
                    v-on:click="deleteStudent(item.id)"
                    icon
                    color="red"
                    style="position: relative; bottom: 2px"
                  >
                    <v-icon id="deleteIcon" size="18px">fas fa-trash</v-icon>
                  </v-btn>

                </td>
              </tr>
              <tr v-if="viewStudent.length < 1">
                <td colspan="7" class="text-center">
                  <h5>No data found</h5>
                </td>
              </tr>
            </tbody>

            <tbody v-if="view.loading">
              <tr v-if="view.loading">
                <td colspan="8">
                  <v-text-field
                    color="rgb(24,103,193)"
                    loading
                    disabled
                  ></v-text-field>
                </td>
              </tr>
            </tbody>
          </template>
        </v-simple-table>

        <v-divider></v-divider>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn
            text
            color="rgb(24,103,193)"
            style="margin-right: 20px"
            @click="getStudentAll"
            >View all information</v-btn
          >

          <Label v-if="viewStudent.length < 1" style="margin-right: 20px"
            >No data found</Label
          >
          <Label v-if="viewStudent.length > 0" style="margin-right: 20px"
            >Found information</Label
          >
        </v-card-actions>
      </v-card>
    </div>

    <!-- popup Row Deatil -->

    <template>
      <v-row justify="center">
        <v-dialog v-model="popup.RowDetail" max-width="700px">
          <v-card style="background-color: #FFFFFF">
            <v-app-bar dark color="#1A76D2">
              <v-toolbar-title><h3>Student Information</h3></v-toolbar-title>

              <v-spacer></v-spacer>
              <v-btn icon>
                <v-icon size="24px" @click="popup.RowDetail = false"
                  >fas fa-times</v-icon
                >
              </v-btn>
            </v-app-bar>

            <v-card-text>
              <v-container
                style="color: black; margin-top: 50px; padding-bottom: 30px"
              >
                <v-row>
                  <v-col cols="8" style="margin: auto">
                    <v-text-field
                      v-model="RowId.Student.id"
                      label="Fill ID"
                      outlined
                      disabled
                    ></v-text-field>
                  </v-col>
                </v-row>

                <v-row>
                  <v-col cols="8" style="margin: auto">
                    <v-text-field
                      v-model="RowId.Student.stuCode"
                      label="Fill Student Code"
                      outlined
                    ></v-text-field>
                  </v-col>
                </v-row>

                <v-row>
                  <v-col cols="8" style="margin: auto">
                    <v-text-field
                      v-model="RowId.Student.name"
                      label="Fill Name"
                      outlined
                    ></v-text-field>
                  </v-col>
                </v-row>

                <v-row>
                  <v-col cols="8" style="margin: auto">
                    <v-text-field
                      v-model="RowId.Student.subject"
                      label="Fill Subject"
                      outlined
                    ></v-text-field>
                  </v-col>
                </v-row>

                <v-row>
                  <v-col cols="8" style="margin: auto">
                    <v-text-field
                      v-model="RowId.Student.tel"
                      label="Fill Phone"
                      outlined
                    ></v-text-field>
                  </v-col>
                </v-row>

                <v-row>
                  <v-spacer></v-spacer>
                  <v-col cols="3">
                    <v-btn
                      class="font-weight-light"
                      color="primary"
                      width="120"
                      height="40"
                      @click="updateStudent"
                      ><h3>Edit</h3></v-btn
                    >
                  </v-col>
                  <v-col cols="3">
                    <v-btn
                      @click="popup.RowDetail = false"
                      class="font-weight-light"
                      color="primary"
                      width="120"
                      height="40"
                      ><h3>Close</h3></v-btn
                    >
                  </v-col>
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
  name: "Table",
  data() {
    return {
      text: "",
      view: {
        data: false,
        loading: false,
      },
      field_search: "",
      viewStudent: [],
      popup: {
        RowDetail: false,
      },
      RowId: {
        Student: {
          id: "",
          stuCode: "",
          name: "",
          subject: "",
          tel: "",
        },
      },
    };
  },
  methods: {
    /* eslint-disable no-console */
    showDataStudent(item) {
      console.log(item);
      this.popup.RowDetail = true;

      //set Data popup Student
      this.RowId.Student.id = item.id;
      this.RowId.Student.stuCode = item.stuCode;
      this.RowId.Student.name = item.name;
      this.RowId.Student.subject = item.subject;
      this.RowId.Student.tel = item.tel;
    },
    getStudentByCode() {
      if (this.field_search != "") {
        this.view.data = false;
        this.view.loading = true;
        http
          .get("/findByStudentCode/" + this.field_search.trim() + "/")
          .then((response) => {
            this.viewStudent = response.data;
            console.log(this.viewStudent);

            if (this.viewStudent.length > 0) {
              this.text = "Found information";
            } else {
              this.text = "No data found";
            }

            this.view.loading = false;
            this.view.data = true;
          })
          .catch((e) => {
            console.log(e);

            this.view.loading = false;
            this.view.data = true;
          });
      }
    },
    deleteStudent(student_id) {
      http
        .delete("/deleteStudent/" + student_id + "/")
        .then((response) => {
          console.log(response.data);

          this.getStudentAll();
        })
        .catch((e) => {
          console.log(e);
        });
    },
    updateStudent() {
      http
        .patch(
          "/updateStudent/" +
            this.RowId.Student.id +
            "/" +
            this.RowId.Student.stuCode +
            "/" +
            this.RowId.Student.name +
            "/" +
            this.RowId.Student.subject +
            "/" +
            this.RowId.Student.tel +
            "/"
        )
        .then((response) => {
          console.log(response.data);
          
          this.popup.RowDetail = false;
          this.getStudentAll();
        })
        .catch((e) => {
          console.log(e);
        });
    },
    getStudentAll() {
      this.view.data = false;
      this.view.loading = true;

      http
        .get("/student/")
        .then((response) => {
          this.viewStudent = response.data;
          console.log(this.viewStudent);

          this.view.loading = false;
          this.view.data = true;
        })
        .catch((e) => {
          console.log(e);

          this.view.loading = false;
          this.view.data = true;
        });
    },
  },
  mounted() {
    this.getStudentAll();
  },
};
</script>